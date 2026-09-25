package com.example.demojava17spring.repository;

import com.example.demojava17spring.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
}
