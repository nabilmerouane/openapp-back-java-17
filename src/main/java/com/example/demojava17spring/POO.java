package com.example.demojava17spring;

enum StatutCompte {
  //    ACTIVE,
  //    BLOKED,
  //    CLOSED,
  ACTIF("Compte actif"),
  BLOQUE("Compte bloqué"),
  FERME("Compte fermé");

  private final String libelle;

  StatutCompte(String libelle) {
    this.libelle = libelle;
  }

  public String getLibelle() {
    return libelle;
  }
}

enum Operation {
  ADDITION {
    @Override
    public int calculer(int a, int b) {
      return a + b;
    }
  },

  SOUSTRACTION {
    @Override
    public int calculer(int a, int b) {
      return a - b;
    }
  },

  DIVISION;

  //    public abstract int calculer(int a, int b);
  public int calculer(int a, int b) {
    return 0;
  }
}

public class POO {
  public static void main(String[] args) throws InterruptedException {

    System.out.println(Operation.ADDITION.calculer(10, 5));
    System.out.println(Operation.SOUSTRACTION.calculer(10, 5));
    System.out.println(Operation.DIVISION.calculer(10, 5));
  }
}
