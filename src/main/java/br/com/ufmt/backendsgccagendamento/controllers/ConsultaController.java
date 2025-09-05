package br.com.ufmt.backendsgccagendamento.controllers;

import br.com.ufmt.backendsgccagendamento.dtos.ConsultaDTO;
import br.com.ufmt.backendsgccagendamento.dtos.HorarioDisponivelDTO;
import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import br.com.ufmt.backendsgccagendamento.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        Consulta novaConsulta = consultaService.agendarConsulta(consultaDTO);
        return ResponseEntity.ok(novaConsulta);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelarConsulta(@PathVariable UUID id) {
        Consulta consultaCancelada = consultaService.cancelarConsulta(id);
        return ResponseEntity.ok(consultaCancelada);
    }

    @GetMapping("/horarios-disponiveis")
    public List<HorarioDisponivelDTO> getHorarios(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return consultaService.listarHorariosDisponiveis(data);
    }
}
