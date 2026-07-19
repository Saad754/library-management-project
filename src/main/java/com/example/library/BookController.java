package com.example.library;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }
    @PostMapping
    public boolean addBook(@RequestBody Book book) {
       return bookService.addBook(book);
    }
    @DeleteMapping("/{id}")
    public boolean deleteBookById(@PathVariable int id) {
        return bookService.deleteBookById(id);
    }


}
