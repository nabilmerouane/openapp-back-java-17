package com.example.demojava17spring;

public class Algorithmes {

  public static int recursivite(int x, int n) {
    if (n == 0) return 1;
    System.out.println("n = " + n);
    return x * recursivite(x, n - 1);
  }

  public static void main(String[] args) {
    System.out.println(recursivite(2, 5));
  }
}
