package br.com.ufmt.backendsgccagendamento.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_especialidade")
    private UUID codigo_especialidade;

    @Column(nullable = false, unique = true, length = 80)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    public Especialidade(UUID codigo_especialidade, String nome, String descricao, LocalDateTime dataAtualizacao, LocalDateTime dataCriacao) {
        this.codigo_especialidade = codigo_especialidade;
        this.nome = nome;
        this.descricao = descricao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
    }

    public Especialidade() {
    }

    public UUID getCodigo_especialidade() {
        return this.codigo_especialidade;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setCodigo_especialidade(UUID codigo_especialidade) {
        this.codigo_especialidade = codigo_especialidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Especialidade)) return false;
        final Especialidade other = (Especialidade) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo_especialidade = this.getCodigo_especialidade();
        final Object other$codigo_especialidade = other.getCodigo_especialidade();
        if (this$codigo_especialidade == null ? other$codigo_especialidade != null : !this$codigo_especialidade.equals(other$codigo_especialidade))
            return false;
        final Object this$nome = this.getNome();
        final Object other$nome = other.getNome();
        if (this$nome == null ? other$nome != null : !this$nome.equals(other$nome)) return false;
        final Object this$descricao = this.getDescricao();
        final Object other$descricao = other.getDescricao();
        if (this$descricao == null ? other$descricao != null : !this$descricao.equals(other$descricao)) return false;
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
        return other instanceof Especialidade;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $codigo_especialidade = this.getCodigo_especialidade();
        result = result * PRIME + ($codigo_especialidade == null ? 43 : $codigo_especialidade.hashCode());
        final Object $nome = this.getNome();
        result = result * PRIME + ($nome == null ? 43 : $nome.hashCode());
        final Object $descricao = this.getDescricao();
        result = result * PRIME + ($descricao == null ? 43 : $descricao.hashCode());
        final Object $dataAtualizacao = this.getDataAtualizacao();
        result = result * PRIME + ($dataAtualizacao == null ? 43 : $dataAtualizacao.hashCode());
        final Object $dataCriacao = this.getDataCriacao();
        result = result * PRIME + ($dataCriacao == null ? 43 : $dataCriacao.hashCode());
        return result;
    }

    public String toString() {
        return "Especialidade(codigo_especialidade=" + this.getCodigo_especialidade() + ", nome=" + this.getNome() + ", descricao=" + this.getDescricao() + ", dataAtualizacao=" + this.getDataAtualizacao() + ", dataCriacao=" + this.getDataCriacao() + ")";
    }
}
