package com.example.demojava17spring;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private static Map<Integer, Integer> carnet = new HashMap<>();

    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (carnet.containsKey(n)) {
            return carnet.get(n);      // déjà calculé : on lit
        }
        int resultat = fib(n-1)  + fib(n-2);      // à toi : la formule habituelle
        carnet.put(n, resultat);       // on note dans le carnet
        return resultat;
    }

    public static void main(String[] args) {
        System.out.println(fib(0));   // 0
        System.out.println(fib(1));   // 1
        System.out.println(fib(4));   // 3
        System.out.println(fib(6));   // 8
        System.out.println(fib(10));   // 8
        System.out.println(fib(40));  // 102334155
    }
}
