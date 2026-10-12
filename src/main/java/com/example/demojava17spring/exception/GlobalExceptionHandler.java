package com.example.demojava17spring.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Handler d'erreurs. */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Gestion erreur NotFound.
   *
   * @param ex exception
   * @return erreur personne non trouvée dans le repository
   */
  @ExceptionHandler(PersonneNotFoundException.class)
  public ProblemDetail handlePersonneNotFound(PersonneNotFoundException ex) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problem.setTitle("Personne introuvable");
    return problem;
  }

  /**
   * Erreur dans les chanmps, invalides.
   *
   * @param ex exception
   * @return erreur de validité des champs des fomulaires
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
    ProblemDetail problem =
        ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST, "La requête contient des champs invalides");
    problem.setTitle("Erreur de validation");

    Map<String, List<String>> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .collect(
                Collectors.groupingBy(
                    FieldError::getField,
                    Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

    problem.setProperty("errors", errors);
    return problem;
  }
}
