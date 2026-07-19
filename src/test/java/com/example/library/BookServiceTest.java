package com.example.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {


    @Test
    public void bookServiceAddBookTest() {
        LibraryConfig config = new LibraryConfig();
        config.setMaxBooks(3);
        BookService bookService = new BookService(config);
        Book book = new Book("Test","Saad","TestCat",1);
        assertTrue(bookService.addBook(book));
    }

    @Test
    public void bookServiceAddBookFail() {
        LibraryConfig config = new LibraryConfig();
        config.setMaxBooks(1);
        BookService bookService = new BookService(config);
        Book book = new Book("Test","Saad","TestCat",1);
        Book book2 = new Book("Test","Saad","TestCat",2);
        bookService.addBook(book);
        assertFalse(bookService.addBook(book2));
    }
    @Test
    public void bookServiceGetBookTest() {
        LibraryConfig config = new LibraryConfig();
        config.setMaxBooks(3);
        BookService bookService = new BookService(config);
        Book book = new Book("Test","Saad","TestCat",1);
        bookService.addBook(book);
        assertEquals("Test",bookService.getBookById(book.getId()).getTitle());
    }
    @Test
    public void bookServiceGetBookFailTest() {
        LibraryConfig config = new LibraryConfig();
        config.setMaxBooks(3);
        BookService bookService = new BookService(config);
        assertNull(bookService.getBookById(1));
    }
    @Test
    public void bookServiceDeleteBookTest() {
        LibraryConfig config = new LibraryConfig();
        config.setMaxBooks(3);
        BookService bookService = new BookService(config);
        Book book = new Book("Test","Saad","TestCat",1);
        bookService.addBook(book);
        assertTrue(bookService.deleteBookById(book.getId()));
        assertNull(bookService.getBookById(book.getId()));
    }
    @Test
    public void bookServiceDeleteBookFailTest() {
        LibraryConfig config = new LibraryConfig();
        config.setMaxBooks(3);
        BookService bookService = new BookService(config);
        assertFalse(bookService.deleteBookById(1));
    }
}
