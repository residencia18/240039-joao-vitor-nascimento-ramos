package br.com.cepedi.Library.api.infra.springDoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

public class SpringDocSettings {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Library API")
                        .description("Rest API of the Library application, containing the CRUD functionalities of books, authors, publishers, customers in addition to the start loan and finalization functionalities")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@library.cepedi"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://library/api/license")));
    }
}
