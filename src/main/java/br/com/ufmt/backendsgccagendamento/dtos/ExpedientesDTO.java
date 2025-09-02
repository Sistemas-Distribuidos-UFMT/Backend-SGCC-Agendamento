package br.com.ufmt.backendsgccagendamento.dtos;

import java.time.LocalTime;
import java.util.Objects;

public class ExpedientesDTO {
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public ExpedientesDTO() {
    }

    public String getDiaSemana() {
        return this.diaSemana;
    }

    public void setDiaSemana(Short dia) {
        if (dia == null) {
            this.diaSemana = null;
            return;
        }
        switch (dia) {
            case 1: this.diaSemana = "Domingo"; break;
            case 2: this.diaSemana = "Segunda-feira"; break;
            case 3: this.diaSemana = "Terça-feira"; break;
            case 4: this.diaSemana = "Quarta-feira"; break;
            case 5: this.diaSemana = "Quinta-feira"; break;
            case 6: this.diaSemana = "Sexta-feira"; break;
            case 7: this.diaSemana = "Sábado"; break;
            default: this.diaSemana = "Dia inválido";
        }
    }

    public LocalTime getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return this.horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpedientesDTO that = (ExpedientesDTO) o;
        return Objects.equals(diaSemana, that.diaSemana) &&
                Objects.equals(horaInicio, that.horaInicio) &&
                Objects.equals(horaFim, that.horaFim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diaSemana, horaInicio, horaFim);
    }

    @Override
    public String toString() {
        return "ExpedientesDTO{" +
                "diaSemana='" + diaSemana + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFim=" + horaFim +
                '}';
    }
}