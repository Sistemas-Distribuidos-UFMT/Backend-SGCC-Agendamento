package br.com.ufmt.backendsgccagendamento.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BadRequestException extends RuntimeException {
    private final String detail;

    public BadRequestException(String detail) {
        super("Requisição inválida");
        this.detail = detail;
    }

    public <T, L> BadRequestException(Class<T> entityClass1, UUID codigo1, Class<L> entityClass2, UUID codigo2) {
        super("Inconsistência de dados identificada.");
        this.detail = String.format(
                "O objeto %s com identificador '%s' não possui relação com o objeto %s com identificador '%s'.",
                entityClass1.getSimpleName(),
                codigo1,
                entityClass2.getSimpleName(),
                codigo2
        );
    }

    public String getDetail() {
        return this.detail;
    }
}
