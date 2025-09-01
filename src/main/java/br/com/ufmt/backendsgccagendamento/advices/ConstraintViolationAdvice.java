package br.com.ufmt.backendsgccagendamento.advices;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ConstraintViolationAdvice extends ResponseEntityExceptionHandler {

    public static class InvalidParam {
        String name;
        String reason;

        public InvalidParam(String name, String reason) {
            this.name = name;
            this.reason = reason;
        }

        public String getName() {
            return this.name;
        }

        public String getReason() {
            return this.reason;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof InvalidParam)) return false;
            final InvalidParam other = (InvalidParam) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
            final Object this$reason = this.getReason();
            final Object other$reason = other.getReason();
            if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof InvalidParam;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $name = this.getName();
            result = result * PRIME + ($name == null ? 43 : $name.hashCode());
            final Object $reason = this.getReason();
            result = result * PRIME + ($reason == null ? 43 : $reason.hashCode());
            return result;
        }

        public String toString() {
            return "ConstraintViolationAdvice.InvalidParam(name=" + this.getName() + ", reason=" + this.getReason() + ")";
        }
    }

    public static class ConstraintViolationProblemDetail extends ProblemDetail {
        List<InvalidParam> invalidParams = new ArrayList<>();

        public ConstraintViolationProblemDetail(List<InvalidParam> invalidParams) {
            this.invalidParams = invalidParams;
        }

        public ConstraintViolationProblemDetail() {
        }

        public static ConstraintViolationProblemDetail fromProblemDetail(ProblemDetail problemDetail) {
            ConstraintViolationProblemDetail novo = new ConstraintViolationProblemDetail();
            novo.setTitle(problemDetail.getTitle());
            novo.setDetail(problemDetail.getDetail());
            novo.setProperties(problemDetail.getProperties());
            novo.setStatus(problemDetail.getStatus());
            novo.setInstance(problemDetail.getInstance());
            novo.setType(problemDetail.getType());
            return novo;
        }

        public List<InvalidParam> getInvalidParams() {
            return this.invalidParams;
        }

        public void setInvalidParams(List<InvalidParam> invalidParams) {
            this.invalidParams = invalidParams;
        }

        public String toString() {
            return "ConstraintViolationAdvice.ConstraintViolationProblemDetail(invalidParams=" + this.getInvalidParams() + ")";
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof ConstraintViolationProblemDetail)) return false;
            final ConstraintViolationProblemDetail other = (ConstraintViolationProblemDetail) o;
            if (!other.canEqual((Object) this)) return false;
            if (!super.equals(o)) return false;
            final Object this$invalidParams = this.getInvalidParams();
            final Object other$invalidParams = other.getInvalidParams();
            if (this$invalidParams == null ? other$invalidParams != null : !this$invalidParams.equals(other$invalidParams))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ConstraintViolationProblemDetail;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = super.hashCode();
            final Object $invalidParams = this.getInvalidParams();
            result = result * PRIME + ($invalidParams == null ? 43 : $invalidParams.hashCode());
            return result;
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ConstraintViolationProblemDetail constraintViolationHandler(ConstraintViolationException e) {
        ConstraintViolationProblemDetail problemDetail = ConstraintViolationProblemDetail.fromProblemDetail(ProblemDetail.forStatus(HttpStatus.BAD_REQUEST));
        problemDetail.setTitle("Parametros invalidos");
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String name = violation.getPropertyPath().toString();
            String reason = violation.getMessage();
            problemDetail.invalidParams.add(new InvalidParam(name, reason));
        }
        return problemDetail;
    }
}
