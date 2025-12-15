package com.example.palapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PalapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalapiApplication.class, args);
        System.out.println("Hello, Palapi!");
        System.out.println("API : -> http://localhost:8080/api/v1/pals");
        System.out.println("Database access : -> http://localhost:8080/h2-console");
        System.out.println("Swagger : -> http://localhost:8080/swagger-ui/index.html");
    }

}
