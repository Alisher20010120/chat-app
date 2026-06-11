package com.example.chatapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.media.StringSchema;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
                );
    }

    @Bean
    public OperationCustomizer globalHeaderCustomizer() {
        return (operation, handlerMethod) -> {
            Parameter acceptLanguageHeader = new Parameter()
                    .name("Accept-Language") // Header nomi
                    .in("header")            // Qayerda joylashishi
                    .required(false)         // Majburiyligi (false bo'lsa default ingliz tili ketadi)
                    .description("Tizim tilini tanlang (Language selection)")
                    .schema(new StringSchema()
                            ._default("uz")  // Default til
                            ._enum(Arrays.asList("uz", "en", "ru"))); // Swaggerda dropdown bo'lib chiqadigan tillar

            // So'rov parametrlari ro'yxatiga ushbu headerni qo'shamiz
            operation.addParametersItem(acceptLanguageHeader);

            return operation;
        };
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Autentifikatsiya (Auth)")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi chatApi() {
        return GroupedOpenApi.builder()
                .group("Chat Tizimi")             // Guruh nomi
                .pathsToMatch("/api/chat/**", "/api/messages/**", "/api/users/**") // Bu guruhga kiruvchi yo'llar
                .build();
    }
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("Kanal")
                .pathsToMatch("/api/room/**")
                .build();
    }
}