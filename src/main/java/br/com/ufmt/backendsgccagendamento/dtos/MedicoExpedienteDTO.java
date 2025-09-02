package br.com.ufmt.backendsgccagendamento.dtos;

import br.com.ufmt.backendsgccagendamento.entities.Especialidade;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MedicoExpedienteDTO {

    private UUID codigo_medico;
    private Pessoa pessoa;
    private Especialidade especialidade;
    private List<ExpedientesDTO> expedientes;
    private String crm;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataCriacao;

    public MedicoExpedienteDTO() {
    }

    public UUID getCodigo_medico() {
        return codigo_medico;
    }

    public void setCodigo_medico(UUID codigo_medico) {
        this.codigo_medico = codigo_medico;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public List<ExpedientesDTO> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(List<ExpedientesDTO> expedientes) {
        this.expedientes = expedientes;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicoExpedienteDTO that = (MedicoExpedienteDTO) o;
        return Objects.equals(codigo_medico, that.codigo_medico) &&
                Objects.equals(pessoa, that.pessoa) &&
                Objects.equals(especialidade, that.especialidade) &&
                Objects.equals(expedientes, that.expedientes) &&
                Objects.equals(crm, that.crm) &&
                Objects.equals(dataAtualizacao, that.dataAtualizacao) &&
                Objects.equals(dataCriacao, that.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo_medico, pessoa, especialidade, expedientes, crm, dataAtualizacao, dataCriacao);
    }

    @Override
    public String toString() {
        return "MedicoExpedienteDTO{" +
                "codigo_medico=" + codigo_medico +
                ", pessoa=" + pessoa +
                ", especialidade=" + especialidade +
                ", expedientes=" + expedientes +
                ", crm='" + crm + '\'' +
                ", dataAtualizacao=" + dataAtualizacao +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}