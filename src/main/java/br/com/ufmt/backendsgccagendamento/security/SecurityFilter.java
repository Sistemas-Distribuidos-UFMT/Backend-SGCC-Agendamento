package br.com.ufmt.backendsgccagendamento.security;

import br.com.ufmt.backendsgccagendamento.dtos.TokenDTO;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import br.com.ufmt.backendsgccagendamento.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    PessoaRepository pessoaRepository;
    public static final String AUTH_TOKEN = "auth_token";
    private static final Integer ONE_SECOND = 1;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            Optional<TokenDTO> possivelLogin = tokenService.validateToken(token);

            if (possivelLogin.isEmpty()) {
                deleteToken(response);
                sendException("Token inválido ou expirado.", "Faça o login novamente.", request, response);
                return;
            }

            TokenDTO tokenDTO = possivelLogin.get();
            UserDetails user = pessoaRepository.findByCodigo_pessoa(tokenDTO.codigoLogin());

            if(user != null) {
                if (!user.isEnabled()){
                    deleteToken(response);
                    sendException("Usuário desativado.", "Contate os administradores.", request, response);
                    return;
                }

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private static String recoverToken(HttpServletRequest request){
        var cookies = request.getCookies();
        if (cookies != null) {
            for (var cookie : cookies) {
                if (AUTH_TOKEN.equals(cookie.getName())) {
                    return cookie.getValue();
                }
                return null;
            }
        }
        return null;
    }

    private static void deleteToken(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(AUTH_TOKEN, null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private static void sendException(String title, String detail, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        writeResponseBody(response, problemDetail);
    }

    private static void writeResponseBody(HttpServletResponse response, Object body) throws IOException {
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, body);
        out.flush();
    }
}
