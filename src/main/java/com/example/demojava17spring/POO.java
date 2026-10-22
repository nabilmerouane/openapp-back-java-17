package com.example.demojava17spring;

class Parent {
  String nom = "Parent";

  String qui() {
    return "Parent";
  }
}

class Enfant extends Parent {
  String nom = "Enfant";

  String qui() {
    return "Enfant";
  }

  void jouer() {
    System.out.println("jouer");
  }
}

class Personne {
  String prenom;

  Personne(String prenom) {
    this.prenom = prenom;
  }
  //
  //    @Override
  //    public boolean equals(Object obj) {
  //        if (this == obj) {
  //            return true;
  //        }
  //
  //        if (!(obj instanceof Personne)) {
  //            return false;
  //        }
  //
  //        Personne autre = (Personne) obj;
  //
  //        return Objects.equals(this.prenom, autre.prenom);
  //    }
  //
  //    @Override
  //    public int hashCode() {
  //        return Objects.hash(prenom);
  //    }
}

class Counter {
  private int count = 0;

  public void increment() {
    count++;
    System.out.println(count);
  }

  public int getCount() {
    return count;
  }
}

public class POO {
  public static void main(String[] args) throws InterruptedException {

    Counter c = new Counter();

    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 1_000_000; i++) {
                c.increment();
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              for (int i = 0; i < 1_000_000; i++) {
                c.increment();
              }
            });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println(c.getCount());
  }
}
