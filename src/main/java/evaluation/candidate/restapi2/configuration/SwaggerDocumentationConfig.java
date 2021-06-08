package evaluation.candidate.restapi2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableTransactionManagement
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Sample Indentity in Project")
            .description("The client API provides standardized mechanism for identity management such as creation, update, retrieval, deletion. ### Resources - Client  Party API performs the following operations : - Retrieve a client - Retrieve a collection of clients according to given criteria - Create a new client - Update an existing client - Delete an existing client")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", ""))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("evaluation.candidate.restapi2"))
                    .build().apiInfo(apiInfo());
    }

}
