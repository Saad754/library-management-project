package com.example.library;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private List<Book> books = new ArrayList<>();
    private final LibraryConfig config;
    public BookService(LibraryConfig config) {
        this.config = config;
    }
    public List<Book> getAllBooks() {
        return books;
    }
    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    public boolean addBook(Book book) {

        if (books.size()<config.getMaxBooks()) {
        books.add(book);
        logger.info("Book added: {}", book.getTitle());
            return true;
        }else{
            logger.warn("Library is full");
        }
        return false;
    }
    public boolean deleteBookById(int id) {
        Book book = getBookById(id);
        if (book != null) {
            books.remove(book);
            logger.info("Book deleted: {}" , book.getTitle());
            return true;
        }else{
            logger.warn("Book not found");
        }
        return false;
    }
}
