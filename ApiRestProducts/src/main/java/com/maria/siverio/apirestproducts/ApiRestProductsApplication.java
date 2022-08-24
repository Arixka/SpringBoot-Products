package com.maria.siverio.apirestproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiRestProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRestProductsApplication.class, args);
    }

}
