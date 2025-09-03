package br.com.ufmt.backendsgccagendamento.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record TokenDTO(
        UUID codigoLogin,
        LocalDateTime dataCriacao) {
}
