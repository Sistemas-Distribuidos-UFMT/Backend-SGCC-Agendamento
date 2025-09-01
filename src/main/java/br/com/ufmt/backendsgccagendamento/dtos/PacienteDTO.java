package br.com.ufmt.backendsgccagendamento.dtos;

public class PacienteDTO {
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    public PacienteDTO() {
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PacienteDTO)) return false;
        final PacienteDTO other = (PacienteDTO) o;
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
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PacienteDTO;
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
        return result;
    }

    public String toString() {
        return "PacienteDTO(nome=" + this.getNome() + ", email=" + this.getEmail() + ", telefone=" + this.getTelefone() + ", senha=" + this.getSenha() + ")";
    }
}
