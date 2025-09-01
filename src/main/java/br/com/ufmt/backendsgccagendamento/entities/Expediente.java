package br.com.ufmt.backendsgccagendamento.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_expediente")
    private UUID codigo_expediente;

    @ManyToOne
    @JoinColumn(name = "codigo_medico", referencedColumnName = "codigo_medico", nullable = false)
    private Medico medico;

    @Column(name = "dia_semana", nullable = false)
    private Short diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    public Expediente(UUID codigo_expediente, Medico medico, Short diaSemana, LocalTime horaInicio, LocalTime horaFim, LocalDateTime dataAtualizacao, LocalDateTime dataCriacao) {
        this.codigo_expediente = codigo_expediente;
        this.medico = medico;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
    }

    public Expediente() {
    }

    public UUID getCodigo_expediente() {
        return this.codigo_expediente;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public Short getDiaSemana() {
        return this.diaSemana;
    }

    public LocalTime getHoraInicio() {
        return this.horaInicio;
    }

    public LocalTime getHoraFim() {
        return this.horaFim;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setCodigo_expediente(UUID codigo_expediente) {
        this.codigo_expediente = codigo_expediente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setDiaSemana(Short diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Expediente)) return false;
        final Expediente other = (Expediente) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo_expediente = this.getCodigo_expediente();
        final Object other$codigo_expediente = other.getCodigo_expediente();
        if (this$codigo_expediente == null ? other$codigo_expediente != null : !this$codigo_expediente.equals(other$codigo_expediente))
            return false;
        final Object this$medico = this.getMedico();
        final Object other$medico = other.getMedico();
        if (this$medico == null ? other$medico != null : !this$medico.equals(other$medico)) return false;
        final Object this$diaSemana = this.getDiaSemana();
        final Object other$diaSemana = other.getDiaSemana();
        if (this$diaSemana == null ? other$diaSemana != null : !this$diaSemana.equals(other$diaSemana)) return false;
        final Object this$horaInicio = this.getHoraInicio();
        final Object other$horaInicio = other.getHoraInicio();
        if (this$horaInicio == null ? other$horaInicio != null : !this$horaInicio.equals(other$horaInicio))
            return false;
        final Object this$horaFim = this.getHoraFim();
        final Object other$horaFim = other.getHoraFim();
        if (this$horaFim == null ? other$horaFim != null : !this$horaFim.equals(other$horaFim)) return false;
        final Object this$dataAtualizacao = this.getDataAtualizacao();
        final Object other$dataAtualizacao = other.getDataAtualizacao();
        if (this$dataAtualizacao == null ? other$dataAtualizacao != null : !this$dataAtualizacao.equals(other$dataAtualizacao))
            return false;
        final Object this$dataCriacao = this.getDataCriacao();
        final Object other$dataCriacao = other.getDataCriacao();
        if (this$dataCriacao == null ? other$dataCriacao != null : !this$dataCriacao.equals(other$dataCriacao))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Expediente;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $codigo_expediente = this.getCodigo_expediente();
        result = result * PRIME + ($codigo_expediente == null ? 43 : $codigo_expediente.hashCode());
        final Object $medico = this.getMedico();
        result = result * PRIME + ($medico == null ? 43 : $medico.hashCode());
        final Object $diaSemana = this.getDiaSemana();
        result = result * PRIME + ($diaSemana == null ? 43 : $diaSemana.hashCode());
        final Object $horaInicio = this.getHoraInicio();
        result = result * PRIME + ($horaInicio == null ? 43 : $horaInicio.hashCode());
        final Object $horaFim = this.getHoraFim();
        result = result * PRIME + ($horaFim == null ? 43 : $horaFim.hashCode());
        final Object $dataAtualizacao = this.getDataAtualizacao();
        result = result * PRIME + ($dataAtualizacao == null ? 43 : $dataAtualizacao.hashCode());
        final Object $dataCriacao = this.getDataCriacao();
        result = result * PRIME + ($dataCriacao == null ? 43 : $dataCriacao.hashCode());
        return result;
    }

    public String toString() {
        return "Expediente(codigo_expediente=" + this.getCodigo_expediente() + ", medico=" + this.getMedico() + ", diaSemana=" + this.getDiaSemana() + ", horaInicio=" + this.getHoraInicio() + ", horaFim=" + this.getHoraFim() + ", dataAtualizacao=" + this.getDataAtualizacao() + ", dataCriacao=" + this.getDataCriacao() + ")";
    }
}
