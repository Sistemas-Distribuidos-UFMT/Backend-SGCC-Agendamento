package br.com.ufmt.backendsgccagendamento.dtos;

import br.com.ufmt.backendsgccagendamento.entities.Pessoa;

public record LoginResponseDTO(
        Pessoa usuario,
        String token
) {
}
