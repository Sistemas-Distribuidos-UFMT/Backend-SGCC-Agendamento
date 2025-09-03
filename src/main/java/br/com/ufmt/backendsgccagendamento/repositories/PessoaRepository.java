package br.com.ufmt.backendsgccagendamento.repositories;

import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.entities.enums.TipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    List<Pessoa> findByTipoPessoa(TipoPessoa tipoPessoa);
    UserDetails findByEmail(String email);
    UserDetails findByCodigo_pessoa(UUID codigo_pessoa);
}
