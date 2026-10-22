package com.example.demojava17spring.controller;

import com.example.demojava17spring.dto.CreatePersonneRequest;
import com.example.demojava17spring.dto.PageResponse;
import com.example.demojava17spring.dto.PersonneResponse;
import com.example.demojava17spring.dto.UpdatePersonneRequest;
import com.example.demojava17spring.repository.PersonneRepository;
import com.example.demojava17spring.service.PersonneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Contrôleur REST pour la gestion des personnes. */
@RestController
@RequestMapping("/api/personnes")
@RequiredArgsConstructor
public class PersonneController {

  private final PersonneService personneService;
  private final PersonneRepository personneRepository;

  /** Récupère toutes les personnes. */
  @GetMapping
  public PageResponse<PersonneResponse> getPersonnes(
      @PageableDefault(size = 20, sort = "id") Pageable pageable) {
    return personneService.getPersonnes(pageable);
  }

  /**
   * Récupère une personne par son identifiant.
   *
   * @param id l'identifiant de la personne
   * @return la personne correspondante
   */
  @GetMapping("/{id}")
  public PersonneResponse getPersonne(@PathVariable Long id) {
    return personneService.getPersonne(id);
  }

  /**
   * Ajoute une personne.
   *
   * @param request de création d'une personne
   * @return la personne céée
   */
  @PostMapping
  public PersonneResponse addPersonne(@Valid @RequestBody CreatePersonneRequest request) {
    return personneService.addPersonne(request);
  }

  /**
   * Modifie une personne.
   *
   * @param id d'une personne
   * @param request d'update d'une personne
   * @return la personne correspondante modifiée
   */
  @PutMapping("/{id}")
  public PersonneResponse updatePersonne(
      @PathVariable Long id, @Valid @RequestBody UpdatePersonneRequest request) {
    return personneService.updatePersonne(id, request);
  }

  /**
   * Supprime une personne.
   *
   * @param id de la personne à supprimer
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
    personneService.deletePersonne(id);
    return ResponseEntity.noContent().build();
  }
}
