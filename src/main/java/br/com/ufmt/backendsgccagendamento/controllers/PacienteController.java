package br.com.ufmt.backendsgccagendamento.controllers;

import br.com.ufmt.backendsgccagendamento.dtos.PacienteDTO;
import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.services.AuthorizationService;
import br.com.ufmt.backendsgccagendamento.services.ConsultaService;
import br.com.ufmt.backendsgccagendamento.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/me")
    public ResponseEntity<Pessoa> buscarPacienteLogado() {
        Pessoa pacienteLogado = authorizationService.getUsuarioAutenticado();
        return ResponseEntity.ok(pacienteLogado);
    }

    @PutMapping("/me")
    public ResponseEntity<Pessoa> atualizarPacienteLogado(@RequestBody PacienteDTO pacienteDTO) {
        Pessoa pacienteLogado = authorizationService.getUsuarioAutenticado();

        Pessoa dadosAtualizados = new Pessoa();
        dadosAtualizados.setNome(pacienteDTO.getNome());
        dadosAtualizados.setEmail(pacienteDTO.getEmail());
        dadosAtualizados.setTelefone(pacienteDTO.getTelefone());

        Pessoa pacienteAtualizado = pacienteService.atualizarPacienteLogado(pacienteLogado, dadosAtualizados);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @GetMapping("/me/consultas")
    public ResponseEntity<List<Consulta>> listarConsultasDoPacienteLogado() {
        Pessoa pacienteLogado = authorizationService.getUsuarioAutenticado();
        List<Consulta> consultas = consultaService.listarConsultasPorPaciente(pacienteLogado.getCodigo_pessoa());
        return ResponseEntity.ok(consultas);
    }

    @GetMapping
    public List<Pessoa> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPacientePorId(@PathVariable UUID id) {
        Pessoa paciente = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public Pessoa criarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(pacienteDTO.getNome());
        novaPessoa.setEmail(pacienteDTO.getEmail());
        novaPessoa.setTelefone(pacienteDTO.getTelefone());
        novaPessoa.setSenhaCriptografada(pacienteDTO.getSenha());

        return pacienteService.salvarPaciente(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPaciente(@PathVariable UUID id, @RequestBody PacienteDTO pacienteDTO) {
        Pessoa dadosAtualizados = new Pessoa();
        dadosAtualizados.setNome(pacienteDTO.getNome());
        dadosAtualizados.setEmail(pacienteDTO.getEmail());
        dadosAtualizados.setTelefone(pacienteDTO.getTelefone());

        Pessoa pacienteAtualizado = pacienteService.atualizarPaciente(id, dadosAtualizados);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable UUID id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}