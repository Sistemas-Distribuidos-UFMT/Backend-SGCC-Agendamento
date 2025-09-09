package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.dtos.ConsultaDTO;
import br.com.ufmt.backendsgccagendamento.dtos.ConsultaStatusDTO;
import br.com.ufmt.backendsgccagendamento.dtos.HorarioDisponivelDTO;
import br.com.ufmt.backendsgccagendamento.entities.Expediente;
import br.com.ufmt.backendsgccagendamento.producers.ConsultaProducer;
import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import br.com.ufmt.backendsgccagendamento.entities.Medico;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.entities.enums.Situacao;
import br.com.ufmt.backendsgccagendamento.exceptions.BadRequestException;
import br.com.ufmt.backendsgccagendamento.exceptions.EntityNotFoundException;
import br.com.ufmt.backendsgccagendamento.repositories.ConsultaRepository;
import br.com.ufmt.backendsgccagendamento.repositories.ExpedienteRepository;
import br.com.ufmt.backendsgccagendamento.repositories.MedicoRepository;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ConsultaProducer consultaProducer;

    @Autowired
    private ExpedienteRepository expedienteRepository;

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public List<Consulta> listarConsultasPorPaciente(UUID pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public Consulta buscarConsultaPorId(UUID id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Consulta.class, id));
    }

    @Transactional
    public Consulta agendarConsulta(ConsultaDTO consultaDTO) {
        Pessoa paciente = pessoaRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException(Pessoa.class, consultaDTO.getPacienteId()));
        Medico medico = medicoRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new EntityNotFoundException(Medico.class, consultaDTO.getMedicoId()));

        LocalDateTime dataConsulta = consultaDTO.getData();

        if (dataConsulta.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Não é possível agendar consultas para datas ou horários passados.");
        }

        if (dataConsulta.isBefore(LocalDateTime.now().plusMinutes(30))) {
            throw new BadRequestException("As consultas devem ser agendadas com no mínimo 30 minutos de antecedência.");
        }

        if (dataConsulta.getMinute() != 0) {
            throw new BadRequestException("As consultas só podem ser agendadas em horas cheias (ex: 14:00, 15:00).");
        }

        DayOfWeek diaDaSemana = dataConsulta.getDayOfWeek();
        short diaDaSemanaNumerico = (short) (diaDaSemana.getValue() % 7 + 1);
        boolean medicoAtende = expedienteRepository.existsByMedicoAndHorario(
                medico.getCodigo_medico(),
                diaDaSemanaNumerico,
                dataConsulta.toLocalTime()
        );
        if (!medicoAtende) {
            throw new BadRequestException("O médico selecionado não atende neste dia e horário.");
        }

        if (consultaRepository.existsByMedicoAndData(consultaDTO.getMedicoId(), dataConsulta)) {
            throw new BadRequestException("Horário indisponível para este médico.");
        }
        if (consultaRepository.existsByPacienteAndData(consultaDTO.getPacienteId(), dataConsulta)) {
            throw new BadRequestException("Paciente já possui uma consulta neste horário.");
        }

        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPessoa(paciente);
        novaConsulta.setData(dataConsulta);
        novaConsulta.setSituacao(Situacao.AGENDADA);

        Consulta consultaSalva = consultaRepository.save(novaConsulta);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        ConsultaStatusDTO statusDTO = new ConsultaStatusDTO(
                paciente.getEmail(),
                consultaSalva.getSituacao().name(),
                paciente.getNome(),
                medico.getPessoa().getNome(),
                consultaSalva.getData().format(formatter)
        );
        consultaProducer.publishMessage(statusDTO);

        return consultaSalva;
    }

    @Transactional
    public Consulta cancelarConsulta(UUID id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Consulta.class, id));

        consulta.setSituacao(Situacao.CANCELADA);
        Consulta consultaCancelada = consultaRepository.save(consulta);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        ConsultaStatusDTO statusDTO = new ConsultaStatusDTO(
                consultaCancelada.getPessoa().getEmail(),
                consultaCancelada.getSituacao().name(),
                consultaCancelada.getPessoa().getNome(),
                consultaCancelada.getMedico().getPessoa().getNome(),
                consultaCancelada.getData().format(formatter)
        );
        consultaProducer.publishMessage(statusDTO);
        return consultaCancelada;
    }

    public List<HorarioDisponivelDTO> listarHorariosDisponiveis(LocalDate data) {
        DayOfWeek diaDaSemana = data.getDayOfWeek();
        short diaDaSemanaNumerico = (short) (diaDaSemana.getValue() % 7 + 1);

        List<Expediente> expedientesDoDia = expedienteRepository.findAllByDiaSemana(diaDaSemanaNumerico);


        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.atTime(LocalTime.MAX);
        List<Consulta> consultasAgendadas = consultaRepository.findAllByDataBetween(inicioDoDia, fimDoDia);


        Map<UUID, List<LocalTime>> horariosOcupadosPorMedico = consultasAgendadas.stream()
                .collect(Collectors.groupingBy(
                        consulta -> consulta.getMedico().getCodigo_medico(),
                        Collectors.mapping(consulta -> consulta.getData().toLocalTime(), Collectors.toList())
                ));


        List<HorarioDisponivelDTO> horariosDisponiveis = new ArrayList<>();
        for (Expediente expediente : expedientesDoDia) {
            LocalTime horaAtual = expediente.getHoraInicio();
            while (horaAtual.isBefore(expediente.getHoraFim())) {

                List<LocalTime> horariosOcupados = horariosOcupadosPorMedico.get(expediente.getMedico().getCodigo_medico());

                if (horariosOcupados == null || !horariosOcupados.contains(horaAtual)) {
                    horariosDisponiveis.add(new HorarioDisponivelDTO(
                            expediente.getMedico().getCodigo_medico(),
                            expediente.getMedico().getPessoa().getNome(),
                            expediente.getMedico().getEspecialidade().getNome(),
                            horaAtual
                    ));
                }


                horaAtual = horaAtual.plusHours(1);
            }
        }
        return horariosDisponiveis;
    }
}