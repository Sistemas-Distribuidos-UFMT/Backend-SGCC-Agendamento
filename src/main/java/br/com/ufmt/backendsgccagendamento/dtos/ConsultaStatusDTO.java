package br.com.ufmt.backendsgccagendamento.dtos;

import java.io.Serializable;

public class ConsultaStatusDTO implements Serializable {
    private String emailPaciente;
    private String situacao;
    private String nomePaciente;
    private String nomeMedico;
    private String dataConsulta;

    public ConsultaStatusDTO(String emailPaciente, String situacao, String nomePaciente, String nomeMedico, String dataConsulta) {
        this.emailPaciente = emailPaciente;
        this.situacao = situacao;
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.dataConsulta = dataConsulta;
    }

    public ConsultaStatusDTO() {
    }

    public String getEmailPaciente() {
        return this.emailPaciente;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public String getNomePaciente() {
        return this.nomePaciente;
    }

    public String getNomeMedico() {
        return this.nomeMedico;
    }

    public String getDataConsulta() {
        return this.dataConsulta;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ConsultaStatusDTO)) return false;
        final ConsultaStatusDTO other = (ConsultaStatusDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$emailPaciente = this.getEmailPaciente();
        final Object other$emailPaciente = other.getEmailPaciente();
        if (this$emailPaciente == null ? other$emailPaciente != null : !this$emailPaciente.equals(other$emailPaciente))
            return false;
        final Object this$situacao = this.getSituacao();
        final Object other$situacao = other.getSituacao();
        if (this$situacao == null ? other$situacao != null : !this$situacao.equals(other$situacao)) return false;
        final Object this$nomePaciente = this.getNomePaciente();
        final Object other$nomePaciente = other.getNomePaciente();
        if (this$nomePaciente == null ? other$nomePaciente != null : !this$nomePaciente.equals(other$nomePaciente))
            return false;
        final Object this$nomeMedico = this.getNomeMedico();
        final Object other$nomeMedico = other.getNomeMedico();
        if (this$nomeMedico == null ? other$nomeMedico != null : !this$nomeMedico.equals(other$nomeMedico))
            return false;
        final Object this$dataConsulta = this.getDataConsulta();
        final Object other$dataConsulta = other.getDataConsulta();
        if (this$dataConsulta == null ? other$dataConsulta != null : !this$dataConsulta.equals(other$dataConsulta))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConsultaStatusDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $emailPaciente = this.getEmailPaciente();
        result = result * PRIME + ($emailPaciente == null ? 43 : $emailPaciente.hashCode());
        final Object $situacao = this.getSituacao();
        result = result * PRIME + ($situacao == null ? 43 : $situacao.hashCode());
        final Object $nomePaciente = this.getNomePaciente();
        result = result * PRIME + ($nomePaciente == null ? 43 : $nomePaciente.hashCode());
        final Object $nomeMedico = this.getNomeMedico();
        result = result * PRIME + ($nomeMedico == null ? 43 : $nomeMedico.hashCode());
        final Object $dataConsulta = this.getDataConsulta();
        result = result * PRIME + ($dataConsulta == null ? 43 : $dataConsulta.hashCode());
        return result;
    }

    public String toString() {
        return "ConsultaStatusDTO(emailPaciente=" + this.getEmailPaciente() + ", situacao=" + this.getSituacao() + ", nomePaciente=" + this.getNomePaciente() + ", nomeMedico=" + this.getNomeMedico() + ", dataConsulta=" + this.getDataConsulta() + ")";
    }
}