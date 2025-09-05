package br.com.ufmt.backendsgccagendamento.dtos;

import java.time.LocalTime;
import java.util.UUID;

public class HorarioDisponivelDTO {
    private UUID medicoId;
    private String nomeMedico;
    private String especialidade;
    private LocalTime horario;

    public HorarioDisponivelDTO(UUID medicoId, String nomeMedico, String especialidade, LocalTime horario) {
        this.medicoId = medicoId;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.horario = horario;
    }

    public HorarioDisponivelDTO() {
    }

    public UUID getMedicoId() {
        return this.medicoId;
    }

    public String getNomeMedico() {
        return this.nomeMedico;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public LocalTime getHorario() {
        return this.horario;
    }

    public void setMedicoId(UUID medicoId) {
        this.medicoId = medicoId;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof HorarioDisponivelDTO)) return false;
        final HorarioDisponivelDTO other = (HorarioDisponivelDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$medicoId = this.getMedicoId();
        final Object other$medicoId = other.getMedicoId();
        if (this$medicoId == null ? other$medicoId != null : !this$medicoId.equals(other$medicoId)) return false;
        final Object this$nomeMedico = this.getNomeMedico();
        final Object other$nomeMedico = other.getNomeMedico();
        if (this$nomeMedico == null ? other$nomeMedico != null : !this$nomeMedico.equals(other$nomeMedico))
            return false;
        final Object this$especialidade = this.getEspecialidade();
        final Object other$especialidade = other.getEspecialidade();
        if (this$especialidade == null ? other$especialidade != null : !this$especialidade.equals(other$especialidade))
            return false;
        final Object this$horario = this.getHorario();
        final Object other$horario = other.getHorario();
        if (this$horario == null ? other$horario != null : !this$horario.equals(other$horario)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HorarioDisponivelDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $medicoId = this.getMedicoId();
        result = result * PRIME + ($medicoId == null ? 43 : $medicoId.hashCode());
        final Object $nomeMedico = this.getNomeMedico();
        result = result * PRIME + ($nomeMedico == null ? 43 : $nomeMedico.hashCode());
        final Object $especialidade = this.getEspecialidade();
        result = result * PRIME + ($especialidade == null ? 43 : $especialidade.hashCode());
        final Object $horario = this.getHorario();
        result = result * PRIME + ($horario == null ? 43 : $horario.hashCode());
        return result;
    }

    public String toString() {
        return "HorarioDisponivelDTO(medicoId=" + this.getMedicoId() + ", nomeMedico=" + this.getNomeMedico() + ", especialidade=" + this.getEspecialidade() + ", horario=" + this.getHorario() + ")";
    }
}