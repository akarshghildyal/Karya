package com.akarsh.karya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.akarsh.karya", "com.akarsh.auth"})
@EnableJpaRepositories(basePackages = "com.akarsh.auth.repository")
@EntityScan(basePackages = "com.akarsh.auth.entity")
public class KaryaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaryaApplication.class, args);
    }
}
