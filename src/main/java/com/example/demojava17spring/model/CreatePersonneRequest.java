package com.example.demojava17spring.model;

/**
 * Objet requête de création d'une personne.
 *
 * @param nom de la personne
 * @param prenom de la personne
 */
public record CreatePersonneRequest(String nom, String prenom) {}
