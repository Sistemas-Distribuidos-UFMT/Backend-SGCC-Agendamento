package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.entities.Especialidade;
import br.com.ufmt.backendsgccagendamento.entities.Medico;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.entities.enums.TipoPessoa;
import br.com.ufmt.backendsgccagendamento.repositories.EspecialidadeRepository;
import br.com.ufmt.backendsgccagendamento.repositories.MedicoRepository;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Medico buscarMedicoPorId(UUID id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public Medico salvarMedico(Medico medico, UUID especialidadeId) {
        Pessoa pessoa = medico.getPessoa();
        pessoa.setTipoPessoa(TipoPessoa.MEDICO);
        pessoaRepository.save(pessoa);

        Especialidade especialidade = especialidadeRepository.findById(especialidadeId)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
        medico.setEspecialidade(especialidade);

        return medicoRepository.save(medico);
    }

    public Medico atualizarMedico(UUID id, Medico medicoAtualizado) {
        Optional<Medico> medicoExistente = medicoRepository.findById(id);
        if (medicoExistente.isPresent()) {
            Medico medico = medicoExistente.get();
            medico.setCrm(medicoAtualizado.getCrm());
            return medicoRepository.save(medico);
        }
        return null;
    }

    public void deletarMedico(UUID id) {
        medicoRepository.deleteById(id);
    }
}
