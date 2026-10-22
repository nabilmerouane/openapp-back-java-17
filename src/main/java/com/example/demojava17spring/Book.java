package com.example.demojava17spring;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book implements Serializable {

  private static final long serialVersionUID = -2936687026040726549L;

  private static final String FILE_NAME = "book.ser";

  private String bookName;

  private transient String description;
  private transient int copies;

  public static void serialize(Book book) throws Exception {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
      out.writeObject(book);
    }
  }

  public static Book deserialize() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
      return (Book) in.readObject();
    }
  }

  public static void main(String[] args) throws Exception {
    Book book = new Book();

    book.setBookName("Java Reference");
    book.setDescription("will not be saved");
    book.setCopies(25);

    // Sérialisation de l'objet
    serialize(book);

    // Désérialisation dans un nouvel objet
    Book restoredBook = deserialize();

    // Vérification des valeurs
    System.out.println(restoredBook.getBookName());
    System.out.println(restoredBook.getDescription());
    System.out.println(restoredBook.getCopies());
  }
}
