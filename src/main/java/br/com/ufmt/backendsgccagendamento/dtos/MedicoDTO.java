package br.com.ufmt.backendsgccagendamento.dtos;

import java.util.UUID;

public class MedicoDTO {
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String crm;
    private UUID especialidadeId;

    public MedicoDTO() {
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getCrm() {
        return this.crm;
    }

    public UUID getEspecialidadeId() {
        return this.especialidadeId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setEspecialidadeId(UUID especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MedicoDTO)) return false;
        final MedicoDTO other = (MedicoDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nome = this.getNome();
        final Object other$nome = other.getNome();
        if (this$nome == null ? other$nome != null : !this$nome.equals(other$nome)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$telefone = this.getTelefone();
        final Object other$telefone = other.getTelefone();
        if (this$telefone == null ? other$telefone != null : !this$telefone.equals(other$telefone)) return false;
        final Object this$senha = this.getSenha();
        final Object other$senha = other.getSenha();
        if (this$senha == null ? other$senha != null : !this$senha.equals(other$senha)) return false;
        final Object this$crm = this.getCrm();
        final Object other$crm = other.getCrm();
        if (this$crm == null ? other$crm != null : !this$crm.equals(other$crm)) return false;
        final Object this$especialidadeId = this.getEspecialidadeId();
        final Object other$especialidadeId = other.getEspecialidadeId();
        if (this$especialidadeId == null ? other$especialidadeId != null : !this$especialidadeId.equals(other$especialidadeId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MedicoDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nome = this.getNome();
        result = result * PRIME + ($nome == null ? 43 : $nome.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $telefone = this.getTelefone();
        result = result * PRIME + ($telefone == null ? 43 : $telefone.hashCode());
        final Object $senha = this.getSenha();
        result = result * PRIME + ($senha == null ? 43 : $senha.hashCode());
        final Object $crm = this.getCrm();
        result = result * PRIME + ($crm == null ? 43 : $crm.hashCode());
        final Object $especialidadeId = this.getEspecialidadeId();
        result = result * PRIME + ($especialidadeId == null ? 43 : $especialidadeId.hashCode());
        return result;
    }

    public String toString() {
        return "MedicoDTO(nome=" + this.getNome() + ", email=" + this.getEmail() + ", telefone=" + this.getTelefone() + ", senha=" + this.getSenha() + ", crm=" + this.getCrm() + ", especialidadeId=" + this.getEspecialidadeId() + ")";
    }
}
