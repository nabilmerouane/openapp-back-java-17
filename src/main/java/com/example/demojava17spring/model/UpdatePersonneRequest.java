package com.example.demojava17spring.model;

/**
 * Objet requête d'update d'une personne.
 *
 * @param prenom de la personne
 * @param nom de la personne
 */
public record UpdatePersonneRequest(String prenom, String nom) {}
