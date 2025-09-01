package br.com.ufmt.backendsgccagendamento.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico implements Serializable {

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
}
