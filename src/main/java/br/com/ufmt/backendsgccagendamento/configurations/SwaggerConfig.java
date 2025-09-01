package br.com.ufmt.backendsgccagendamento.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("SGCC - Serviço de Agendamento")
                        .description("Documentação da API do Serviço de Agendamento do SGCC (Sistema de Gestão de Clínicas e Consultórios)")
                        .version("v1.0")
                );
    }
}