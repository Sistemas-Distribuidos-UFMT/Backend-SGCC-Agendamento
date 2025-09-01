package br.com.ufmt.backendsgccagendamento.controllers;

import br.com.ufmt.backendsgccagendamento.dtos.ConsultaDTO;
import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import br.com.ufmt.backendsgccagendamento.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> listarConsultas() {
        return consultaService.listarConsultas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaPorId(@PathVariable UUID id) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        return consulta != null ? ResponseEntity.ok(consulta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        try {
            Consulta novaConsulta = consultaService.agendarConsulta(consultaDTO);
            return ResponseEntity.ok(novaConsulta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable UUID id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
