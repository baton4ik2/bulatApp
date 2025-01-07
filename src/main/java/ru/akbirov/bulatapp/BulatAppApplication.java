package ru.akbirov.bulatapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "API для управления группами и студентами",
                version = "1.0",
                description = "Документация API для управления группами и студентами в учебном заведении"
        )
)

@SpringBootApplication
public class BulatAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulatAppApplication.class, args);
    }

}
