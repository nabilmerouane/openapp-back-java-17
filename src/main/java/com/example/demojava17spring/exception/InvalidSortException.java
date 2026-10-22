package com.example.demojava17spring.exception;

// exception/InvalidSortException.java
public class InvalidSortException extends RuntimeException {
  public InvalidSortException(String property) {
    super("Tri non autorisé sur : " + property);
  }
}
