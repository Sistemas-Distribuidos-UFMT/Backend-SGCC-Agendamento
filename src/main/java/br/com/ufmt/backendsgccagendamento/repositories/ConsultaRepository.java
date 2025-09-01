package br.com.ufmt.backendsgccagendamento.repositories;

import br.com.ufmt.backendsgccagendamento.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

    @Query("SELECT c FROM Consulta c WHERE c.medico.codigo_medico = :medicoId")
    List<Consulta> findByMedicoId(@Param("medicoId") UUID medicoId);

    @Query("SELECT c FROM Consulta c WHERE c.pessoa.codigo_pessoa = :pacienteId")
    List<Consulta> findByPacienteId(@Param("pacienteId") UUID pacienteId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Consulta c WHERE c.medico.codigo_medico = :medicoId AND c.data = :data")
    boolean existsByMedicoAndData(@Param("medicoId") UUID medicoId, @Param("data") LocalDateTime data);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Consulta c WHERE c.pessoa.codigo_pessoa = :pacienteId AND c.data = :data")
    boolean existsByPacienteAndData(@Param("pacienteId") UUID pacienteId, @Param("data") LocalDateTime data);
}