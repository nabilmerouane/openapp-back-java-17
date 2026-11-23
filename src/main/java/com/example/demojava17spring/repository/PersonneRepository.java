package com.example.demojava17spring.repository;

import com.example.demojava17spring.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository JPA pour l'accès aux données des personnes.
 */
public interface PersonneRepository
        extends JpaRepository<Personne, Long>,
        JpaSpecificationExecutor<Personne> {
}