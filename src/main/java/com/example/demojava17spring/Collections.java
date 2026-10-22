package com.example.demojava17spring;

import java.util.ArrayList;
import java.util.List;

class Produit {
  String nom;
  String couleur;

  Produit(String nom, String couleur) {
    this.nom = nom;
    this.couleur = couleur;
  }
}

public class Collections {
  public static void main(String[] args) {

    //        class Cle {
    //            String id;
    //
    //            Cle(String id) {
    //                this.id = id;
    //            }
    //
    //            @Override
    //            public boolean equals(Object o) {
    //                return o instanceof Cle c && c.id.equals(this.id);
    //            }
    //
    //            @Override
    //            public int hashCode() {
    //                return id.hashCode();
    //            }
    //        }
    //
    //        Cle k = new Cle("X");
    //        Set<Cle> set = new HashSet<>();
    //        set.add(k);
    //
    //        k.id = "Y";
    //
    //        System.out.println(set.size());               // (N)
    //        System.out.println(set.contains(k));          // (O)
    //        System.out.println(set.contains(new Cle("X"))); // (P)
    //        System.out.println(set.contains(new Cle("Y"))); // (Q)
    //        Produit p1 = new Produit("Stylo", "");
    //        Produit p2 = new Produit("Stylo", "Blanc");
    //
    //        List<Produit> list = new ArrayList<>();
    //        Set<Produit> set = new HashSet<>();
    //
    //        list.add(p1);
    //        list.add(p1);
    //        list.add(p2);
    //        System.out.println("list.size() : " + list.size());               // (A)
    //        System.out.println(set.add(p1));               // (B)
    //        System.out.println(set.add(p1));               // (C)
    //        System.out.println(set.add(p2));               // (D)
    //        System.out.println("set.size() : " + set.size());                // (E)
    //        System.out.println(set.contains(new Produit("Stylo", ""))); // (F)
    //        System.out.println(set.contains(p1)); // (F)
    //        System.out.println(set.contains(p2)); // (F)
    //        System.out.println(" ****************************** ");
    //        Map<String, Integer> map = new HashMap<>();
    //
    //        System.out.println(map.put("a", 1));    // (G)
    //        System.out.println(map.put("a", 2));    // (H)
    //        System.out.println(map.put(null, 3));   // (I)
    //        System.out.println(map.put(null, 4));   // (J)
    //        System.out.println(map.size());         // (K)
    //        System.out.println(map.get("a"));       // (L)
    //        System.out.println(map.get("zzz"));     // (M)
    //        System.out.println(map.get(null));     // (M)

    List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));
    System.out.println(nums.size()); // (M)

    for (Integer n : nums) {
      if (n == 3) {
        System.out.println(nums.remove(n));
        nums.remove(n);
      }
    }

    System.out.println(nums.size()); // (M)
  }
}
