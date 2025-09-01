package br.com.ufmt.backendsgccagendamento.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_medico")
    private UUID codigo_medico;

    @OneToOne
    @JoinColumn(name = "codigo_pessoa", referencedColumnName = "codigo_pessoa", nullable = false, unique = true)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "codigo_especialidade", referencedColumnName = "codigo_especialidade", nullable = false)
    private Especialidade especialidade;

    @Column(nullable = false, unique = true, length = 50)
    private String crm;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    public Medico(UUID codigo_medico, Pessoa pessoa, Especialidade especialidade, String crm, LocalDateTime dataAtualizacao, LocalDateTime dataCriacao) {
        this.codigo_medico = codigo_medico;
        this.pessoa = pessoa;
        this.especialidade = especialidade;
        this.crm = crm;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
    }

    public Medico() {
    }

    public UUID getCodigo_medico() {
        return this.codigo_medico;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public Especialidade getEspecialidade() {
        return this.especialidade;
    }

    public String getCrm() {
        return this.crm;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setCodigo_medico(UUID codigo_medico) {
        this.codigo_medico = codigo_medico;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Medico)) return false;
        final Medico other = (Medico) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo_medico = this.getCodigo_medico();
        final Object other$codigo_medico = other.getCodigo_medico();
        if (this$codigo_medico == null ? other$codigo_medico != null : !this$codigo_medico.equals(other$codigo_medico))
            return false;
        final Object this$pessoa = this.getPessoa();
        final Object other$pessoa = other.getPessoa();
        if (this$pessoa == null ? other$pessoa != null : !this$pessoa.equals(other$pessoa)) return false;
        final Object this$especialidade = this.getEspecialidade();
        final Object other$especialidade = other.getEspecialidade();
        if (this$especialidade == null ? other$especialidade != null : !this$especialidade.equals(other$especialidade))
            return false;
        final Object this$crm = this.getCrm();
        final Object other$crm = other.getCrm();
        if (this$crm == null ? other$crm != null : !this$crm.equals(other$crm)) return false;
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
        return other instanceof Medico;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $codigo_medico = this.getCodigo_medico();
        result = result * PRIME + ($codigo_medico == null ? 43 : $codigo_medico.hashCode());
        final Object $pessoa = this.getPessoa();
        result = result * PRIME + ($pessoa == null ? 43 : $pessoa.hashCode());
        final Object $especialidade = this.getEspecialidade();
        result = result * PRIME + ($especialidade == null ? 43 : $especialidade.hashCode());
        final Object $crm = this.getCrm();
        result = result * PRIME + ($crm == null ? 43 : $crm.hashCode());
        final Object $dataAtualizacao = this.getDataAtualizacao();
        result = result * PRIME + ($dataAtualizacao == null ? 43 : $dataAtualizacao.hashCode());
        final Object $dataCriacao = this.getDataCriacao();
        result = result * PRIME + ($dataCriacao == null ? 43 : $dataCriacao.hashCode());
        return result;
    }

    public String toString() {
        return "Medico(codigo_medico=" + this.getCodigo_medico() + ", pessoa=" + this.getPessoa() + ", especialidade=" + this.getEspecialidade() + ", crm=" + this.getCrm() + ", dataAtualizacao=" + this.getDataAtualizacao() + ", dataCriacao=" + this.getDataCriacao() + ")";
    }
}
