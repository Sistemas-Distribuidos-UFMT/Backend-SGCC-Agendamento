package br.com.ufmt.backendsgccagendamento.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class MedicoDTO {
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String crm;
    private UUID especialidadeId;
}
