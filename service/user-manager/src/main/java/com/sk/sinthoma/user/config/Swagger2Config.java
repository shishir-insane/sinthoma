package com.sk.sinthoma.user.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class Swagger2Config {
    @Bean
    public Docket userManagerApi() {
	return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	          .apis(RequestHandlerSelectors.any())
	          .paths(PathSelectors.any())
	          .build()
	        .pathMapping("/sinthoma/user-manager")
	        .directModelSubstitute(LocalDate.class, String.class)
	        .genericModelSubstitutes(ResponseEntity.class)
	        .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
	return new ApiInfoBuilder().title("User Manager API").description("Core APIs to access user details")
		.contact(new Contact("Shishir Kumar", "https://github.com/shishir-insane/sinthoma",
			"shishir.insane@gmail.com"))
		.license("GNU Lesser General Public License v3.0")
		.licenseUrl("https://github.com/shishir-insane/sinthoma/blob/master/LICENSE").version("1.0.0").build();
    }
}