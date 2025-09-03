package br.com.ufmt.backendsgccagendamento.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("SGCC - Serviço de Agendamento")
                        .description("Documentação da API do Serviço de Agendamento do SGCC (Sistema de Gestão de Clínicas e Consultórios)")
                        .version("v1.0")
                )
                .path("/api/auth/logout", new PathItem()
                        .post(new Operation()
                                .summary("Logout")
                                .description("Realiza o logout do usuário")
                                .operationId("logoutUser")
                                .tags(List.of("Autenticação"))
                                .responses(new ApiResponses()
                                        .addApiResponse("200", new ApiResponse()
                                                .description("OK"))
                                )
                        )
                );
    }
}