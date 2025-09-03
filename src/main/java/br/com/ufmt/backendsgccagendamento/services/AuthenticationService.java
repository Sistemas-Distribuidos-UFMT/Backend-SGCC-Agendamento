package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.dtos.AuthenticationDTO;
import br.com.ufmt.backendsgccagendamento.dtos.LoginResponseDTO;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.exceptions.EntityNotFoundException;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import br.com.ufmt.backendsgccagendamento.validations.ValidateUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PessoaRepository pessoaRepository;

    @Lazy
    public AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService, PessoaRepository pessoaRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.pessoaRepository = pessoaRepository;
    }

    public LoginResponseDTO loginValidate(AuthenticationDTO data) {
        ValidateUtil.validateOrThrow(data);

        Pessoa login = (Pessoa) pessoaRepository.findByEmail(data.email());

        if (login == null)
            throw new EntityNotFoundException(Pessoa.class, data.email());

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Pessoa) auth.getPrincipal());

        return new LoginResponseDTO(login, token);
    }

}
