package br.com.ufmt.backendsgccagendamento.dtos;

import lombok.Data;

@Data
public class PacienteDTO {
    private String nome;
    private String email;
    private String telefone;
    private String senha;
}
