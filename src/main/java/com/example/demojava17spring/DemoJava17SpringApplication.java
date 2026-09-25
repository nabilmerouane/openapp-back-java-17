package com.example.demojava17spring;

import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.repository.PersonneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoJava17SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJava17SpringApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PersonneRepository personneRepository) {
        return args -> {
            personneRepository.save(new Personne(1, "Dupont", "Jean"));
            personneRepository.save(new Personne(2, "Martin", "Sophie"));
            personneRepository.save(new Personne(3, "Durand", "Paul"));
        };
    }

}
