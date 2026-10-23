package com.example.demojava17spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Modèle de personne. */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Personne {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;
  private String prenom;

  public void modifier(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }

  /**
   * Normalise la persistence du nom en lettres CAPITALES de la personne lors de la création et de
   * l'update
   */
  @PrePersist
  @PreUpdate
  private void normaliser() {
    if (nom != null) {
      nom = nom.trim().toUpperCase(Locale.ROOT);
    }
  }
}
