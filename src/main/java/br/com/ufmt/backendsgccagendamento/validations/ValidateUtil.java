package br.com.ufmt.backendsgccagendamento.validations;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class ValidateUtil {
    private static Validator validator;
    public ValidateUtil(Validator validator) {
        this.validator = validator;
    }

    public static <T> void validateOrThrow(T entity) {
        var violations = validator.validate(entity);
        if(!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
