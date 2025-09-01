package br.com.ufmt.backendsgccagendamento.entities;

import br.com.ufmt.backendsgccagendamento.entities.enums.Situacao;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_consulta")
    private UUID codigo_consulta;

    @ManyToOne
    @JoinColumn(name = "codigo_pessoa", referencedColumnName = "codigo_pessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "codigo_medico", referencedColumnName = "codigo_medico", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    public Consulta(UUID codigo_consulta, Pessoa pessoa, Medico medico, LocalDateTime data, Situacao situacao, LocalDateTime dataAtualizacao, LocalDateTime dataCriacao) {
        this.codigo_consulta = codigo_consulta;
        this.pessoa = pessoa;
        this.medico = medico;
        this.data = data;
        this.situacao = situacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
    }

    public Consulta() {
    }

    public UUID getCodigo_consulta() {
        return this.codigo_consulta;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public Situacao getSituacao() {
        return this.situacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setCodigo_consulta(UUID codigo_consulta) {
        this.codigo_consulta = codigo_consulta;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Consulta)) return false;
        final Consulta other = (Consulta) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$codigo_consulta = this.getCodigo_consulta();
        final Object other$codigo_consulta = other.getCodigo_consulta();
        if (this$codigo_consulta == null ? other$codigo_consulta != null : !this$codigo_consulta.equals(other$codigo_consulta))
            return false;
        final Object this$pessoa = this.getPessoa();
        final Object other$pessoa = other.getPessoa();
        if (this$pessoa == null ? other$pessoa != null : !this$pessoa.equals(other$pessoa)) return false;
        final Object this$medico = this.getMedico();
        final Object other$medico = other.getMedico();
        if (this$medico == null ? other$medico != null : !this$medico.equals(other$medico)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        final Object this$situacao = this.getSituacao();
        final Object other$situacao = other.getSituacao();
        if (this$situacao == null ? other$situacao != null : !this$situacao.equals(other$situacao)) return false;
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
        return other instanceof Consulta;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $codigo_consulta = this.getCodigo_consulta();
        result = result * PRIME + ($codigo_consulta == null ? 43 : $codigo_consulta.hashCode());
        final Object $pessoa = this.getPessoa();
        result = result * PRIME + ($pessoa == null ? 43 : $pessoa.hashCode());
        final Object $medico = this.getMedico();
        result = result * PRIME + ($medico == null ? 43 : $medico.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        final Object $situacao = this.getSituacao();
        result = result * PRIME + ($situacao == null ? 43 : $situacao.hashCode());
        final Object $dataAtualizacao = this.getDataAtualizacao();
        result = result * PRIME + ($dataAtualizacao == null ? 43 : $dataAtualizacao.hashCode());
        final Object $dataCriacao = this.getDataCriacao();
        result = result * PRIME + ($dataCriacao == null ? 43 : $dataCriacao.hashCode());
        return result;
    }

    public String toString() {
        return "Consulta(codigo_consulta=" + this.getCodigo_consulta() + ", pessoa=" + this.getPessoa() + ", medico=" + this.getMedico() + ", data=" + this.getData() + ", situacao=" + this.getSituacao() + ", dataAtualizacao=" + this.getDataAtualizacao() + ", dataCriacao=" + this.getDataCriacao() + ")";
    }
}
