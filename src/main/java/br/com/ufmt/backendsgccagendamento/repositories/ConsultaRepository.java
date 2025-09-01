package br.com.ufmt.backendsgccagendamento.repositories;

import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
    List<Consulta> findByMedicoCodigo(UUID medicoId);
    List<Consulta> findByPessoaCodigo(UUID pacienteId);
    boolean existsByMedicoCodigoAndData(UUID medicoId, LocalDateTime data);
    boolean existsByPessoaCodigoAndData(UUID pacienteId, LocalDateTime data);
}
