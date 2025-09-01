package br.com.ufmt.backendsgccagendamento.exceptions;

public class ForbiddenAccessException extends RuntimeException {
    private final String detail;

    public ForbiddenAccessException(String detail) {
        super("Acesso negado");
        this.detail = detail;
    }

    public String getDetail() {
        return this.detail;
    }
}
