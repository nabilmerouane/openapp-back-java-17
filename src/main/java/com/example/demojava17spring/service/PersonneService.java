package com.example.demojava17spring.service;

import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.repository.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {

    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> getPersonnes() {
        return personneRepository.findAll();
    }

    public Personne getPersonne(int personneId) {
        return personneRepository.findById(personneId)
                .orElseThrow(() -> new RuntimeException("Personne non trouvée"));
    }

    public Personne addPersonne(Personne personne) {
        return personneRepository.save(personne);
    }
}
