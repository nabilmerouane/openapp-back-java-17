package com.example.demojava17spring.model;

/**
 * Objet requête de création d'une personne.
 *
 * @param id de la personne
 * @param prenom de la personne
 * @param nom de la personne
 */
public record PersonneResponse(Long id, String prenom, String nom) {}
