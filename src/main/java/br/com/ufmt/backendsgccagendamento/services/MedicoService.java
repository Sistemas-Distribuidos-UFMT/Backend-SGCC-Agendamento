package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.dtos.ExpedientesDTO;
import br.com.ufmt.backendsgccagendamento.dtos.MedicoExpedienteDTO;
import br.com.ufmt.backendsgccagendamento.entities.Especialidade;
import br.com.ufmt.backendsgccagendamento.entities.Expediente;
import br.com.ufmt.backendsgccagendamento.entities.Medico;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.entities.enums.TipoPessoa;
import br.com.ufmt.backendsgccagendamento.exceptions.EntityNotFoundException;
import br.com.ufmt.backendsgccagendamento.repositories.EspecialidadeRepository;
import br.com.ufmt.backendsgccagendamento.repositories.ExpedienteRepository;
import br.com.ufmt.backendsgccagendamento.repositories.MedicoRepository;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired private MedicoRepository medicoRepository;
    @Autowired private PessoaRepository pessoaRepository;
    @Autowired private EspecialidadeRepository especialidadeRepository;
    @Autowired private ExpedienteRepository expedienteRepository;

    public List<MedicoExpedienteDTO> listarMedicos() {
        return medicoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MedicoExpedienteDTO buscarMedicoPorId(UUID id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Medico.class, id));
        return convertToDto(medico);
    }

    private MedicoExpedienteDTO convertToDto(Medico medico) {
        MedicoExpedienteDTO dto = new MedicoExpedienteDTO();

        dto.setCodigo_medico(medico.getCodigo_medico());
        dto.setPessoa(medico.getPessoa());
        dto.setEspecialidade(medico.getEspecialidade());
        dto.setCrm(medico.getCrm());
        dto.setDataAtualizacao(medico.getDataAtualizacao());
        dto.setDataCriacao(medico.getDataCriacao());

        List<Expediente> expedientes = expedienteRepository.findByMedicoId(medico.getCodigo_medico());
        List<ExpedientesDTO> expedientesDTOs = expedientes.stream().map(expediente -> {
            ExpedientesDTO expDto = new ExpedientesDTO();
            expDto.setDiaSemana(expediente.getDiaSemana());
            expDto.setHoraInicio(expediente.getHoraInicio());
            expDto.setHoraFim(expediente.getHoraFim());
            return expDto;
        }).collect(Collectors.toList());

        dto.setExpedientes(expedientesDTOs);

        return dto;
    }

    public List<Especialidade> listarEspecialidades() {
        return especialidadeRepository.findAll();
    }

    @Transactional
    public Medico salvarMedico(Medico medico, UUID especialidadeId) {
        Pessoa pessoa = medico.getPessoa();
        pessoa.setTipoPessoa(TipoPessoa.MEDICO);
        pessoaRepository.save(pessoa);

        Especialidade especialidade = especialidadeRepository.findById(especialidadeId)
                .orElseThrow(() -> new EntityNotFoundException(Especialidade.class, especialidadeId));
        medico.setEspecialidade(especialidade);

        return medicoRepository.save(medico);
    }

    @Transactional
    public Medico atualizarMedico(UUID id, Medico medicoAtualizado) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Medico.class, id));
        medico.setCrm(medicoAtualizado.getCrm());
        return medicoRepository.save(medico);
    }

    @Transactional
    public void deletarMedico(UUID id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Medico.class, id));

        Pessoa pessoa = medico.getPessoa();
        medicoRepository.delete(medico);

        if (pessoa != null) {
            pessoaRepository.delete(pessoa);
        }
    }
}