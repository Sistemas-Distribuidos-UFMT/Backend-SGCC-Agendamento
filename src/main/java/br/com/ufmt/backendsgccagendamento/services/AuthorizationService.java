package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final PessoaRepository pessoaRepository;

    @Lazy
    public AuthorizationService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pessoaRepository.findByEmail(username);
    }


    public Pessoa getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa pessoa = (Pessoa) authentication.getPrincipal();
        return pessoa;
    }
}
