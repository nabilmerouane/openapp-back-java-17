package com.example.demojava17spring.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Objet requête d'update d'une personne.
 *
 * @param nom de la personne
 * @param prenom de la personne
 */
public record UpdatePersonneRequest(@NotBlank String nom, @NotBlank String prenom) {}
