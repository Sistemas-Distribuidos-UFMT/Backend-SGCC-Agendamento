package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.entities.enums.TipoPessoa;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PacienteService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarPacientes() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPacientePorId(UUID id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public Pessoa salvarPaciente(Pessoa paciente) {
        paciente.setTipoPessoa(TipoPessoa.CLIENTE);
        return pessoaRepository.save(paciente);
    }

    public Pessoa atualizarPaciente(UUID id, Pessoa pacienteAtualizado) {
        Optional<Pessoa> pacienteExistente = pessoaRepository.findById(id);
        if (pacienteExistente.isPresent()) {
            Pessoa paciente = pacienteExistente.get();
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setEmail(pacienteAtualizado.getEmail());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            return pessoaRepository.save(paciente);
        }
        return null;
    }

    public void deletarPaciente(UUID id) {
        pessoaRepository.deleteById(id);
    }
}
