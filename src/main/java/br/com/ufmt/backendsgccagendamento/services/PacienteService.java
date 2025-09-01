package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.entities.enums.TipoPessoa;
import br.com.ufmt.backendsgccagendamento.exceptions.EntityNotFoundException;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarPacientes() {
        return pessoaRepository.findByTipoPessoa(TipoPessoa.CLIENTE);
    }

    public Pessoa buscarPacientePorId(UUID id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Pessoa.class, id));
    }

    @Transactional
    public Pessoa salvarPaciente(Pessoa paciente) {
        paciente.setTipoPessoa(TipoPessoa.CLIENTE);
        return pessoaRepository.save(paciente);
    }

    @Transactional
    public Pessoa atualizarPaciente(UUID id, Pessoa pacienteAtualizado) {
        Pessoa paciente = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Pessoa.class, id));

        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setEmail(pacienteAtualizado.getEmail());
        paciente.setTelefone(pacienteAtualizado.getTelefone());
        return pessoaRepository.save(paciente);
    }

    @Transactional
    public void deletarPaciente(UUID id) {
        if(!pessoaRepository.existsById(id)){
            throw new EntityNotFoundException(Pessoa.class, id);
        }
        pessoaRepository.deleteById(id);
    }
}