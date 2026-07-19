package com.example.library;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {
    private final BorrowService borrowService;
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }
    @PostMapping("/borrow/{userId}/{bookId}")
    public String borrowBook(@PathVariable int bookId,@PathVariable int userId){
        return borrowService.borrowBook(bookId,userId);
    }
    @PostMapping("/return/{bookId}")
    public String returnBook(@PathVariable int bookId){
        return borrowService.returnBook(bookId);
    }


}
