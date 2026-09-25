package com.example.demojava17spring.controller;

import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.service.PersonneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public List<Personne> getPersonnes() {
        return personneService.getPersonnes();
    }

    @GetMapping("/{id}")
    public Personne getPersonne(@PathVariable int id) {
        return personneService.getPersonne(id);
    }

    @PostMapping
    public Personne addPersonne(@RequestBody Personne personne) {
        return personneService.addPersonne(personne);
    }
}
