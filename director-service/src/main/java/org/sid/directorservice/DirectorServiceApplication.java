package org.sid.directorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DirectorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectorServiceApplication.class, args);
    }

    @Bean
    WebClient webClient(){
        return WebClient.builder().build();
    }
}
