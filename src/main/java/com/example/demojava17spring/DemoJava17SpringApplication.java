package com.example.demojava17spring;

import com.example.demojava17spring.model.Role;
import com.example.demojava17spring.model.Utilisateur;
import com.example.demojava17spring.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Classe principale de démarrage de l'application Spring Boot. */
@SpringBootApplication
public class DemoJava17SpringApplication {
  /**
   * Point d'entrée de l'application.
   *
   * @param args les arguments de la ligne de commande
   */
  public static void main(String[] args) {
    SpringApplication.run(DemoJava17SpringApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner init(UtilisateurRepository repository, PasswordEncoder encoder) {

    return args -> {
      if (repository.count() == 0) {
        repository.save(new Utilisateur(null, "admin", encoder.encode("admin123"), Role.ADMIN));
        repository.save(new Utilisateur(null, "user", encoder.encode("user123"), Role.USER));
        repository.save(
            new Utilisateur(null, "developer", encoder.encode("developer123"), Role.DEVELOPER));
        repository.save(
            new Utilisateur(null, "manager", encoder.encode("manager123"), Role.MANAGER));
      }
    };
  }
}
