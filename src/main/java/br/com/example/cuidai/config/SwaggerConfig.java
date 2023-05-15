package br.com.example.cuidai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean
    public Docket liferayEvpApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.example.cuidai"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "CUIDAI API",
                "REST API for CUIDAI application",
                "1.0",
                "Terms of Service",
                new Contact("Squad CUIDAI", "https://github.com/icgsbr/cuidai", ""),
                "Apache License Version 2.0",
                "",
                new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
