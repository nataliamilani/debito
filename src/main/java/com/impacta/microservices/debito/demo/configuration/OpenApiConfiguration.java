package com.impacta.microservices.debito.demo.configuration;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = @Info(title = "Debito" , version = "v1",
description = "documentação de API Debito"))
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.components(new Components())
				.info(
						new io.swagger.v3.oas.models.info.Info()
						.title("Debito API")
						.version("v1")
						.license( new License().name("Apache 2.0").url("http://springdoc.org")));
				
		
	}
	

}