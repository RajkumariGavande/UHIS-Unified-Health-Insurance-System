package com.admin.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI adminServiceApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("UHIS Admin Service API")
                        .description("APIs for Managing CaseWorkers and Plans")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Admin Service Team")
                                .email("admin@uhis.com")));
    }
}