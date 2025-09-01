package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.dtos.ConsultaDTO;
import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import br.com.ufmt.backendsgccagendamento.entities.Medico;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.repositories.ConsultaRepository;
import br.com.ufmt.backendsgccagendamento.repositories.MedicoRepository;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Data
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
        return consultaRepository.findById(id).orElse(null);
    }

    public Consulta agendarConsulta(ConsultaDTO consultaDTO) {
        if (consultaRepository.existsByMedicoAndData(consultaDTO.getMedicoId(), consultaDTO.getData())) {
            throw new RuntimeException("Horário indisponível para este médico.");
        }

        if (consultaRepository.existsByPacienteAndData(consultaDTO.getPacienteId(), consultaDTO.getData())) {
            throw new RuntimeException("Paciente já possui uma consulta neste horário.");
        }

        Medico medico = medicoRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        Pessoa paciente = pessoaRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPessoa(paciente);
        novaConsulta.setData(consultaDTO.getData());
        return consultaRepository.save(novaConsulta);
    }

    public void cancelarConsulta(UUID id) {
        consultaRepository.deleteById(id);
    }
}
