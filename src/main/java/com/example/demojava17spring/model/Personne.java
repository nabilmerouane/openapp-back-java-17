package com.example.demojava17spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Modèle de personne. */
@Getter
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
}
