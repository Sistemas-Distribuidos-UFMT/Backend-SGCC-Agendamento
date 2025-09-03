package br.com.ufmt.backendsgccagendamento.exceptions;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    private final String detail;

    public <T> EntityNotFoundException(Class<T> entityClass, UUID codigo) {
        super(String.format("%s não econtrado(a).", entityClass.getSimpleName()));
        this.detail = String.format("O objeto %s com identificador '%s' não existe em nossos registros.", entityClass.getSimpleName(), codigo);
    }
    public <T> EntityNotFoundException(Class<T> entityClass, String email) {
        super(String.format("%s não econtrado(a).", entityClass.getSimpleName()));
        this.detail = String.format("O objeto %s com identificador '%s' não existe em nossos registros.", entityClass.getSimpleName(), email);
    }

    public String getDetail() {
        return this.detail;
    }
}
