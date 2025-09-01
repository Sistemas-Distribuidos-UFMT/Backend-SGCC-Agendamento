package br.com.ufmt.backendsgccagendamento.controllers;

import br.com.ufmt.backendsgccagendamento.dtos.MedicoDTO;
import br.com.ufmt.backendsgccagendamento.entities.Medico;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> listarMedicos() {
        return medicoService.listarMedicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable UUID id) {
        Medico medico = medicoService.buscarMedicoPorId(id);
        return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Medico criarMedico(@RequestBody MedicoDTO medicoDTO) {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(medicoDTO.getNome());
        novaPessoa.setEmail(medicoDTO.getEmail());
        novaPessoa.setTelefone(medicoDTO.getTelefone());
        novaPessoa.setSenhaCriptografada(medicoDTO.getSenha()); // Idealmente, a senha seria criptografada aqui

        Medico novoMedico = new Medico();
        novoMedico.setPessoa(novaPessoa);
        novoMedico.setCrm(medicoDTO.getCrm());

        return medicoService.salvarMedico(novoMedico, medicoDTO.getEspecialidadeId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizarMedico(@PathVariable UUID id, @RequestBody Medico medico) {
        Medico medicoAtualizado = medicoService.atualizarMedico(id, medico);
        return medicoAtualizado != null ? ResponseEntity.ok(medicoAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable UUID id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
