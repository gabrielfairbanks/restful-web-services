package com.fairbanks.restfulwebservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SpringFoxConfig {

    public static final Contact CONTACT = new Contact("Gabriel Fairbanks", "","gabriel.fairbanks@gmail.com");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("MicroServices REST API Documentation","Documentation of API for the Microservices with Springboot course","1.0","",CONTACT,"","", Arrays.asList());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO);
    }
}
