package com.example.demojava17spring.controller;

import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.service.PersonneService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Contrôleur REST pour la gestion des personnes. */
@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

  private final PersonneService personneService;

  public PersonneController(PersonneService personneService) {
    this.personneService = personneService;
  }

  /** Récupère toutes les personnes. */
  @GetMapping
  public List<Personne> getPersonnes() {
    return personneService.getPersonnes();
  }

  /**
   * Récupère une personne par son identifiant.
   *
   * @param id l'identifiant de la personne
   * @return la personne correspondante
   */
  @GetMapping("/{id}")
  public Personne getPersonne(@PathVariable int id) {
    return personneService.getPersonne(id);
  }

  /** Ajoute une personne. */
  @PostMapping
  public Personne addPersonne(@RequestBody Personne personne) {
    return personneService.addPersonne(personne);
  }
}
