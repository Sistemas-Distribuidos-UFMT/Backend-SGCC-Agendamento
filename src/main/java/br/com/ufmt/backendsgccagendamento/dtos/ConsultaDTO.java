package br.com.ufmt.backendsgccagendamento.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ConsultaDTO {
    private UUID pacienteId;
    private UUID medicoId;
    private LocalDateTime data;
}
