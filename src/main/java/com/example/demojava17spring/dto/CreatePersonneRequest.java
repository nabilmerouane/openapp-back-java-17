package com.example.demojava17spring.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Objet requête de création d'une personne.
 *
 * @param nom de la personne
 * @param prenom de la personne
 */
public record CreatePersonneRequest(@NotBlank String nom, @NotBlank String prenom) {}
