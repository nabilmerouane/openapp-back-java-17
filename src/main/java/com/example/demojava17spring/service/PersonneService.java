package com.example.demojava17spring.service;

import com.example.demojava17spring.model.CreatePersonneRequest;
import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.model.PersonneResponse;
import com.example.demojava17spring.model.UpdatePersonneRequest;
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
  public List<PersonneResponse> getPersonnes() {
    return personneRepository.findAll().stream()
        .map(
            personne ->
                new PersonneResponse(personne.getId(), personne.getPrenom(), personne.getNom()))
        .toList();
  }

  /**
   * Récupère une personne par son identifiant.
   *
   * @param personneId l'identifiant de la personne recherchée
   * @return la personne correspondante
   */
  public PersonneResponse getPersonne(Long personneId) {

    Personne personne =
        personneRepository
            .findById(personneId)
            .orElseThrow(() -> new PersonneNotFoundException(personneId));

    return new PersonneResponse(personne.getId(), personne.getPrenom(), personne.getNom());
  }

  /**
   * Ajoute une nouvelle personne.
   *
   * @param request de la personne à ajouter
   * @return la personne créée
   */
  public PersonneResponse addPersonne(CreatePersonneRequest request) {

    Personne personne = new Personne(null, request.nom(), request.prenom());

    Personne savedPersonne = personneRepository.save(personne);

    return new PersonneResponse(
        savedPersonne.getId(), savedPersonne.getPrenom(), savedPersonne.getNom());
  }

  /**
   * Modifie une personne.
   *
   * @param id de la personne
   * @param request de modification
   */
  public PersonneResponse updatePersonne(Long id, UpdatePersonneRequest request) {

    Personne personne =
        personneRepository.findById(id).orElseThrow(() -> new PersonneNotFoundException(id));

    personne.modifier(request.nom(), request.prenom());

    Personne updatedPersonne = personneRepository.save(personne);

    return new PersonneResponse(
        updatedPersonne.getId(), updatedPersonne.getPrenom(), updatedPersonne.getNom());
  }
}
