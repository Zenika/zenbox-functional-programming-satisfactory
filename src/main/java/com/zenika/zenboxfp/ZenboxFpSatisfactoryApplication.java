package com.zenika.zenboxfp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
public class ZenboxFpSatisfactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZenboxFpSatisfactoryApplication.class, args);
    }

    @Bean
    public Parc parc() {
        return new Parc(
            List.of(
                new Usine(5),
                new Usine(10),
                new Usine(15),
                new Usine(20)
            ),
            100
        );
    }
}
