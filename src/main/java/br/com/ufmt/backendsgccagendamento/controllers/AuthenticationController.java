package br.com.ufmt.backendsgccagendamento.controllers;

import br.com.ufmt.backendsgccagendamento.dtos.AuthenticationDTO;
import br.com.ufmt.backendsgccagendamento.dtos.LoginResponseDTO;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.services.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    public static final String AUTH_TOKEN = "auth_token";
    public static final Duration ONE_YEAR_AGE = Duration.ofDays(365);

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("api/auth/login")
    public ResponseEntity<Pessoa> login(@RequestBody AuthenticationDTO data, HttpServletResponse response) {

        LoginResponseDTO loginResponseDTO = authenticationService.loginValidate(data);
        var token = loginResponseDTO.token();
        Pessoa usuario = loginResponseDTO.usuario();

        ResponseCookie cookie = ResponseCookie.from(AUTH_TOKEN, token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(ONE_YEAR_AGE)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(usuario);
    }
}
