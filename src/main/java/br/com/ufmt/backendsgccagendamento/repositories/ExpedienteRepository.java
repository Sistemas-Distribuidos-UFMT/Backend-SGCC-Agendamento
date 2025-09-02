package br.com.ufmt.backendsgccagendamento.repositories;

import br.com.ufmt.backendsgccagendamento.entities.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, UUID> {

    @Query("SELECT e FROM Expediente e WHERE e.medico.codigo_medico = :medicoId")
    List<Expediente> findByMedicoId(@Param("medicoId") UUID medicoId);
}