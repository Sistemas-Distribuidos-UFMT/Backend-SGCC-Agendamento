package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.entities.Especialidade;
import br.com.ufmt.backendsgccagendamento.entities.Medico;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.entities.enums.TipoPessoa;
import br.com.ufmt.backendsgccagendamento.exceptions.EntityNotFoundException;
import br.com.ufmt.backendsgccagendamento.repositories.EspecialidadeRepository;
import br.com.ufmt.backendsgccagendamento.repositories.MedicoRepository;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public List<Especialidade> listarEspecialidades() {
        return especialidadeRepository.findAll();
    }

    public Medico buscarMedicoPorId(UUID id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Medico.class, id));
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