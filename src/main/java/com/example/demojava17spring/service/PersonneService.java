package com.example.demojava17spring.service;

import com.example.demojava17spring.dto.CreatePersonneRequest;
import com.example.demojava17spring.dto.PageResponse;
import com.example.demojava17spring.dto.PersonneResponse;
import com.example.demojava17spring.dto.UpdatePersonneRequest;
import com.example.demojava17spring.exception.InvalidSortException;
import com.example.demojava17spring.exception.PersonneNotFoundException;
import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.repository.PersonneRepository;
import java.util.Set;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service métier pour la gestion des personnes. */
@Service
public class PersonneService {

  private final PersonneRepository personneRepository;
  public static final Set<String> SORTABLE_FIELDS = Set.of("id", "nom", "prenom");

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
   * @param pageable
   * @return toutes les personnes
   */
  @Transactional(readOnly = true)
  public PageResponse<PersonneResponse> getPersonnes(Pageable pageable) {
    validateSort(pageable.getSort());
    Pageable stable =
        PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            pageable.getSort().and(Sort.by("id")));
    return PageResponse.from(personneRepository.findAll(stable).map(PersonneResponse::from));
  }

  private void validateSort(Sort sort) {
    sort.forEach(
        order -> {
          if (!SORTABLE_FIELDS.contains(order.getProperty())) {
            throw new InvalidSortException(order.getProperty());
          }
        });
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

  /**
   * Supprime une personne.
   *
   * @param id de la personne à supprimer
   */
  public void deletePersonne(Long id) {
    Personne personne =
        personneRepository.findById(id).orElseThrow(() -> new PersonneNotFoundException(id));
    personneRepository.delete(personne);
  }
}
