package com.example.demojava17spring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTest {

  @Test
  void testSerialization() throws Exception {
    Book book = new Book();
    book.setBookName("Java Reference");
    book.setDescription("will not be saved");
    book.setCopies(25);

    Book.serialize(book);
    Book restoredBook = Book.deserialize();

    assertEquals("Java Reference", restoredBook.getBookName());
    assertNull(restoredBook.getDescription());
    assertEquals(0, restoredBook.getCopies());
  }
}
