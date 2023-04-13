package guru.springfamework.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.webmvc.api.OpenApiActuatorResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI swaggerData(){
        return new OpenAPI().info(new Info().title("Dereddy").
                        contact(new Contact().name("Hahha"))).
                externalDocs(new ExternalDocumentation().description("Swagge3243423r"));
    }
}
