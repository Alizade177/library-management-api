package com.farid.libraryapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenAPI libraryApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Library Management API")
                        .description("REST API for managing books,authors and members")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }
}
