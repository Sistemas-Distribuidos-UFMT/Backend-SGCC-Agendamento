package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.dtos.ConsultaDTO;
import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import br.com.ufmt.backendsgccagendamento.entities.Medico;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.exceptions.BadRequestException;
import br.com.ufmt.backendsgccagendamento.exceptions.EntityNotFoundException;
import br.com.ufmt.backendsgccagendamento.repositories.ConsultaRepository;
import br.com.ufmt.backendsgccagendamento.repositories.MedicoRepository;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarConsultaPorId(UUID id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Consulta.class, id));
    }

    @Transactional
    public Consulta agendarConsulta(ConsultaDTO consultaDTO) {
        if (consultaRepository.existsByMedicoAndData(consultaDTO.getMedicoId(), consultaDTO.getData())) {
            throw new BadRequestException("Horário indisponível para este médico.");
        }

        if (consultaRepository.existsByPacienteAndData(consultaDTO.getPacienteId(), consultaDTO.getData())) {
            throw new BadRequestException("Paciente já possui uma consulta neste horário.");
        }

        Medico medico = medicoRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new EntityNotFoundException(Medico.class, consultaDTO.getMedicoId()));
        Pessoa paciente = pessoaRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException(Pessoa.class, consultaDTO.getPacienteId()));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPessoa(paciente);
        novaConsulta.setData(consultaDTO.getData());
        return consultaRepository.save(novaConsulta);
    }

    @Transactional
    public void cancelarConsulta(UUID id) {
        if(!consultaRepository.existsById(id)){
            throw new EntityNotFoundException(Consulta.class, id);
        }
        consultaRepository.deleteById(id);
    }
}