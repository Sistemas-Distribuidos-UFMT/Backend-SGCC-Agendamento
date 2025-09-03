package br.com.ufmt.backendsgccagendamento.dtos;

import jakarta.validation.constraints.*;

public record AuthenticationDTO(
        @NotNull(message = "Email não pode ser deixado em branco.")
        @NotBlank(message = "Email não pode ser deixado em branco.")
        @Email
        String email,
        @Size(min = 4, max = 256, message = "Senha precisa conter entre 6 e 256 caracteres.")
        @NotNull(message = "Senha não pode ser deixado em branco.")
        @NotBlank(message = "Senha não pode ser deixado em branco.")
        String senha) {
}
