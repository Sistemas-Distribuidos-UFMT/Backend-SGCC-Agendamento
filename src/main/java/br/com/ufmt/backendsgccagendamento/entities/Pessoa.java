package br.com.ufmt.backendsgccagendamento.entities;

import br.com.ufmt.backendsgccagendamento.entities.enums.TipoPessoa;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_pessoa")
    private UUID codigo_pessoa;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, unique = true, length = 80)
    private String email;

    @Column(length = 15)
    private String telefone;

    @Column(name = "senha_criptografada", nullable = false, length = 256)
    private String senhaCriptografada;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    public Pessoa(UUID codigo_pessoa, String nome, String email, String telefone, String senhaCriptografada, TipoPessoa tipoPessoa, LocalDateTime dataAtualizacao, LocalDateTime dataCriacao) {
        this.codigo_pessoa = codigo_pessoa;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senhaCriptografada = senhaCriptografada;
        this.tipoPessoa = tipoPessoa;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
    }

    public Pessoa() {
    }

    public UUID getCodigo_pessoa() {
        return this.codigo_pessoa;
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

    public String getSenhaCriptografada() {
        return this.senhaCriptografada;
    }

    public TipoPessoa getTipoPessoa() {
        return this.tipoPessoa;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setCodigo_pessoa(UUID codigo_pessoa) {
        this.codigo_pessoa = codigo_pessoa;
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

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Pessoa)) return false;
        final Pessoa other = (Pessoa) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo_pessoa = this.getCodigo_pessoa();
        final Object other$codigo_pessoa = other.getCodigo_pessoa();
        if (this$codigo_pessoa == null ? other$codigo_pessoa != null : !this$codigo_pessoa.equals(other$codigo_pessoa))
            return false;
        final Object this$nome = this.getNome();
        final Object other$nome = other.getNome();
        if (this$nome == null ? other$nome != null : !this$nome.equals(other$nome)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$telefone = this.getTelefone();
        final Object other$telefone = other.getTelefone();
        if (this$telefone == null ? other$telefone != null : !this$telefone.equals(other$telefone)) return false;
        final Object this$senhaCriptografada = this.getSenhaCriptografada();
        final Object other$senhaCriptografada = other.getSenhaCriptografada();
        if (this$senhaCriptografada == null ? other$senhaCriptografada != null : !this$senhaCriptografada.equals(other$senhaCriptografada))
            return false;
        final Object this$tipoPessoa = this.getTipoPessoa();
        final Object other$tipoPessoa = other.getTipoPessoa();
        if (this$tipoPessoa == null ? other$tipoPessoa != null : !this$tipoPessoa.equals(other$tipoPessoa))
            return false;
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
        return other instanceof Pessoa;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $codigo_pessoa = this.getCodigo_pessoa();
        result = result * PRIME + ($codigo_pessoa == null ? 43 : $codigo_pessoa.hashCode());
        final Object $nome = this.getNome();
        result = result * PRIME + ($nome == null ? 43 : $nome.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $telefone = this.getTelefone();
        result = result * PRIME + ($telefone == null ? 43 : $telefone.hashCode());
        final Object $senhaCriptografada = this.getSenhaCriptografada();
        result = result * PRIME + ($senhaCriptografada == null ? 43 : $senhaCriptografada.hashCode());
        final Object $tipoPessoa = this.getTipoPessoa();
        result = result * PRIME + ($tipoPessoa == null ? 43 : $tipoPessoa.hashCode());
        final Object $dataAtualizacao = this.getDataAtualizacao();
        result = result * PRIME + ($dataAtualizacao == null ? 43 : $dataAtualizacao.hashCode());
        final Object $dataCriacao = this.getDataCriacao();
        result = result * PRIME + ($dataCriacao == null ? 43 : $dataCriacao.hashCode());
        return result;
    }

    public String toString() {
        return "Pessoa(codigo_pessoa=" + this.getCodigo_pessoa() + ", nome=" + this.getNome() + ", email=" + this.getEmail() + ", telefone=" + this.getTelefone() + ", senhaCriptografada=" + this.getSenhaCriptografada() + ", tipoPessoa=" + this.getTipoPessoa() + ", dataAtualizacao=" + this.getDataAtualizacao() + ", dataCriacao=" + this.getDataCriacao() + ")";
    }
}