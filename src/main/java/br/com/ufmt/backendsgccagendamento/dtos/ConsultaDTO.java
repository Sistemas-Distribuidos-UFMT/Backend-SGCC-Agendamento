package br.com.ufmt.backendsgccagendamento.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultaDTO {
    private UUID pacienteId;
    private UUID medicoId;
    private LocalDateTime data;

    public ConsultaDTO() {
    }

    public UUID getPacienteId() {
        return this.pacienteId;
    }

    public UUID getMedicoId() {
        return this.medicoId;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setMedicoId(UUID medicoId) {
        this.medicoId = medicoId;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ConsultaDTO)) return false;
        final ConsultaDTO other = (ConsultaDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$pacienteId = this.getPacienteId();
        final Object other$pacienteId = other.getPacienteId();
        if (this$pacienteId == null ? other$pacienteId != null : !this$pacienteId.equals(other$pacienteId))
            return false;
        final Object this$medicoId = this.getMedicoId();
        final Object other$medicoId = other.getMedicoId();
        if (this$medicoId == null ? other$medicoId != null : !this$medicoId.equals(other$medicoId)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConsultaDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $pacienteId = this.getPacienteId();
        result = result * PRIME + ($pacienteId == null ? 43 : $pacienteId.hashCode());
        final Object $medicoId = this.getMedicoId();
        result = result * PRIME + ($medicoId == null ? 43 : $medicoId.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "ConsultaDTO(pacienteId=" + this.getPacienteId() + ", medicoId=" + this.getMedicoId() + ", data=" + this.getData() + ")";
    }
}
