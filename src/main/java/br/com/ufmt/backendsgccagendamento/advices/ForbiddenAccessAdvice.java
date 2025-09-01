package br.com.ufmt.backendsgccagendamento.advices;

import br.com.ufmt.backendsgccagendamento.exceptions.ForbiddenAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ForbiddenAccessAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ForbiddenAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ProblemDetail forbiddenAccessHandler(ForbiddenAccessException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setDetail(e.getDetail());
        return problemDetail;
    }
}
