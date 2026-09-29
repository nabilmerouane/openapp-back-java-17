package com.example.demojava17spring.service;

/** Gestion d'erreur si pas de personne trouvée en base. */
public class PersonneNotFoundException extends RuntimeException {

  /**
   * Message d'erreur de personne non trouvée.
   *
   * @param id de la personne
   */
  public PersonneNotFoundException(Long id) {
    super("Personne non trouvée avec l'id : " + id);
  }
}
