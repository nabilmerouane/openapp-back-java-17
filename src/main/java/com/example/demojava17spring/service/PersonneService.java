package com.example.demojava17spring.service;

import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.repository.PersonneRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/** Service métier pour la gestion des personnes. */
@Service
public class PersonneService {

  private final PersonneRepository personneRepository;

  /**
   * Construit le service avec le repository de personnes.
   *
   * @param personneRepository le repository utilisé pour accéder aux données des personnes
   */
  public PersonneService(PersonneRepository personneRepository) {
    this.personneRepository = personneRepository;
  }

  /**
   * Récupère la liste de toutes les personnes.
   *
   * @return toutes les personnes
   */
  public List<Personne> getPersonnes() {
    return personneRepository.findAll();
  }

  /**
   * Récupère une personne par son identifiant.
   *
   * @param personneId l'identifiant de la personne recherchée
   * @return la personne correspondante
   */
  public Personne getPersonne(int personneId) {
    return personneRepository
        .findById(personneId)
        .orElseThrow(() -> new RuntimeException("Personne non trouvée"));
  }

  /**
   * Ajoute une nouvelle personne.
   *
   * @param personne la personne à ajouter
   * @return la personne créée
   */
  public Personne addPersonne(Personne personne) {
    return personneRepository.save(personne);
  }
}
