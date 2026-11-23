package com.example.demojava17spring.specification;

import com.example.demojava17spring.dto.PersonneSearchCriteria;
import com.example.demojava17spring.model.Personne;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PersonneSpecification {

    public static Specification<Personne> search(
            PersonneSearchCriteria criteria) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (criteria.nom() != null && !criteria.nom().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("nom")),
                                "%" + criteria.nom().toLowerCase() + "%"
                        )
                );
            }

            if (criteria.prenom() != null && !criteria.prenom().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("prenom")),
                                "%" + criteria.prenom().toLowerCase() + "%"
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
