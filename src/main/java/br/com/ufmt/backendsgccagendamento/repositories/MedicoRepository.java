package br.com.ufmt.backendsgccagendamento.repositories;

import br.com.ufmt.backendsgccagendamento.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {
}
