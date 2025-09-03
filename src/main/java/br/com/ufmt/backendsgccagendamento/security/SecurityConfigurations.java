package br.com.ufmt.backendsgccagendamento.security;

import br.com.ufmt.backendsgccagendamento.security.components.CustomLogoutSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    public SecurityConfigurations(CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .deleteCookies("auth_token")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(customLogoutSuccessHandler))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/v3/api-docs.yaml").permitAll()
                        .requestMatchers("/api/swagger-ui/**").permitAll()
                        .requestMatchers("/api/docs").permitAll()

                        .requestMatchers(HttpMethod.POST,   "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,   "/api/v1/auth/esqueci-minha-senha").permitAll()
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/auth/redefinir-senha-token").permitAll()
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/auth/redefinir-senha").hasRole("USUARIO")

                        .requestMatchers(HttpMethod.GET,    "/api/v1/questionario/{id}").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET,    "/api/v1/questionario/abertos").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET,    "/api/v1/questionario/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.POST,   "/api/v1/questionario/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/questionario/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/questionario/**").hasRole("GESTOR")

                        .requestMatchers(HttpMethod.POST,   "/api/v1/usuario").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.POST,   "/api/v1/usuario/candidato").permitAll()
                        .requestMatchers(HttpMethod.POST,   "/api/v1/usuario/enviar-email-validacao").permitAll()
                        .requestMatchers(HttpMethod.POST,   "/api/v1/usuario/imagem").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/usuario").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/usuario/validar-email").permitAll()
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/usuario/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.GET,    "/api/v1/usuario").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET,    "/api/v1/usuario/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/usuario/imagem").hasRole("USUARIO")

                        .requestMatchers(HttpMethod.POST,   "/api/v1/gabarito/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET,    "api/v1/gabarito/all").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.GET,    "api/v1/gabarito/all-avaliador").hasRole("AVALIADOR")
                        .requestMatchers(HttpMethod.GET,    "/api/v1/gabarito/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/gabarito/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/gabarito/**").hasRole("USUARIO")

                        .requestMatchers(HttpMethod.GET,    "api/v1/avaliacao").hasRole("AVALIADOR")
                        .requestMatchers(HttpMethod.GET,    "api/v1/avaliacao/**").hasRole("AVALIADOR")
                        .requestMatchers(HttpMethod.GET,    "api/v1/avaliacao/all").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.POST,   "api/v1/avaliacao/gabarito/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/avaliacao/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.POST,   "api/v1/avaliacao/*/resposta/**").hasRole("AVALIADOR")
                        .requestMatchers(HttpMethod.PUT,    "api/v1/avaliacao/*/resposta/**").hasRole("AVALIADOR")

                        .requestMatchers(HttpMethod.GET,    "api/v1/ranking/*").hasRole("GESTOR")

                        .requestMatchers(HttpMethod.GET,    "api/v1/arquivo/**").hasRole("USUARIO")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingConfigurer())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public AccessDeniedHandler accessDeniedHandler(){
        return (request, response, accessDeniedException) -> {

            ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
            problemDetail.setInstance(URI.create(request.getRequestURI()));
            problemDetail.setTitle("Acesso negado.");
            problemDetail.setDetail("Contate os administradores.");
            //problemDetail.setType(URI.create("url sobre o problema"));

            response.setStatus(problemDetail.getStatus());
            response.setContentType("application/json");

            writeResponseBody(response, problemDetail);
        };
    }

    private static void writeResponseBody(HttpServletResponse response, Object body) throws IOException {
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, body);
        out.flush();
    }

    public Customizer<ExceptionHandlingConfigurer<HttpSecurity>> exceptionHandlingConfigurer() {
        return configurer -> configurer.accessDeniedHandler(accessDeniedHandler());
    }

}
