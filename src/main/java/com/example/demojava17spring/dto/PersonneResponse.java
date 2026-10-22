package com.example.demojava17spring.dto;

import com.example.demojava17spring.model.Personne;

/**
 * Objet requête de création d'une personne.
 *
 * @param id de la personne
 * @param prenom de la personne
 * @param nom de la personne
 */
public record PersonneResponse(Long id, String prenom, String nom) {
  public static PersonneResponse from(Personne personne) {
    return new PersonneResponse(personne.getId(), personne.getPrenom(), personne.getNom());
  }
}
