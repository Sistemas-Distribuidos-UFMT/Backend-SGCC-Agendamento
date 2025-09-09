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

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(customLogoutSuccessHandler))
                .authorizeHttpRequests(authorize -> authorize
                        // Rotas públicas
                        .requestMatchers("/v3/api-docs/**", "/api/swagger-ui/**", "/api/docs").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/pacientes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/consultas/horarios-disponiveis").permitAll()

                        // Rotas do Paciente Logado (/me)
                        .requestMatchers(HttpMethod.GET, "/api/pacientes/me", "/api/pacientes/me/consultas").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/pacientes/me").hasRole("CLIENTE")

                        // Rotas de Pacientes (gerenciamento)
                        .requestMatchers(HttpMethod.GET, "/api/pacientes", "/api/pacientes/{id}").hasRole("ATENDENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/pacientes/{id}").hasRole("ATENDENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/pacientes/{id}").hasRole("GESTOR")

                        // Rotas de Médicos
                        .requestMatchers(HttpMethod.GET, "/api/medicos/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medicos").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.PUT, "/api/medicos/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/medicos/**").hasRole("GESTOR")

                        // Rotas de Consultas
                        .requestMatchers(HttpMethod.GET, "/api/consultas/**").hasAnyRole("ATENDENTE", "MEDICO")
                        .requestMatchers(HttpMethod.POST, "/api/consultas").hasAnyRole("ATENDENTE", "CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/consultas/**").hasAnyRole("ATENDENTE", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/consultas/**").hasAnyRole("ATENDENTE", "CLIENTE")

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
            problemDetail.setDetail("Você não tem permissão para acessar este recurso.");
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