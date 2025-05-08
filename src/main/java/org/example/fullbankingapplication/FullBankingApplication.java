package org.example.fullbankingapplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "apploicaion de gestion banquaire",
        description = "backend application",
        version = "v1.0",
        contact = @Contact(
                name = "Mohamed karafi",
                email = "karafimhd@gmail.com",
                url = "mygithub.com"
        ),
        license = @License(
                name = "the java full application ",
                url = "github.com"
        )
),
        externalDocs = @ExternalDocumentation(
                description = "",
                url = "link of documentation"
        )


)
public class FullBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullBankingApplication.class, args);
    }

}























/*@OpenAPIDefinition(info = @Info(
            title = "Full java Banking Application",
            description = "Backend Rest APIs for FJB",
            version = "v1.0" ,
            contact = @Contact(
                    name ="Mohamed karafi",
                    email = "karafimhd@gmail.com",
                    url = "https://github.com/mohkarafi/FullBankingProject"
            ),
            license = @License(
                    name = "The Java Banking App",
                    url = "https://github.com/mohkarafi/FullBankingProject"
            )
        ),
        externalDocs = @ExternalDocumentation(
                description = "the java Bank Application Documentation",
                url = "https://github.com/mohkarafi/FullBankingProject/tree/main#readme"
        )
)*/