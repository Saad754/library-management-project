package com.example.library;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowService {
    private List<BorrowRecord> records = new ArrayList<>();
    private final BookService bookService;
    private final LibraryConfig config;
    private final int overDueBooksLimit = 3;
    private final int borrowedBooksLimit = 5;
    private int idCounter;

    public BorrowService(BookService bookService, LibraryConfig config) {
        this.bookService = bookService;
        this.config = config;
    }

    private boolean isBookCurrentlyBorrowed(int bookId) {
        for  (BorrowRecord record : records) {
            if(record.getBookId() == bookId && record.getStatus().equals("Borrowed")) {
                return true;
            }
        }
        return false;
    }
    private int countActiveBorrows(int userId) {
        int count =0;
        for  (BorrowRecord record : records) {
            if(record.getUserId() == userId && record.getStatus().equals("Borrowed")) {
                count++;
            }
        }
        return count;
    }
    private int countOverdueBooks(int userId) {
        int count =0;
        for  (BorrowRecord record : records) {
            if(record.getUserId() == userId && !record.getStatus().equals("Returned")) {
                if (record.getDueDate().isBefore(LocalDate.now())) {
                    count++;
                }
            }
        }
        return count;
    }

    private BorrowRecord findActiveBorrowRecord(int bookId){
        for (BorrowRecord record : records) {
            if(record.getBookId() == bookId && record.getStatus().equals("Borrowed")) {
                return record;
            }
        }
        return null;
    }
    public String borrowBook(int bookId,int userId){
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            return "Book Not Found";
        }else if (isBookCurrentlyBorrowed(bookId)) {
            return "Book is already Borrowed";
        }else if (countActiveBorrows(userId)>= borrowedBooksLimit){
            return "Limit to borrowed books is 5. Return a book before you borrow another one";
        }else if (countOverdueBooks(userId)>= overDueBooksLimit){
            return "Overdue limit is reached. Return a book before borrowing a new one";
        }else {
            BorrowRecord newRecord= new BorrowRecord(bookId, userId, LocalDate.now().plusDays(config.getLoanPeriodDays()));
            newRecord.setBorrowId(idCounter);
            idCounter++;
            records.add(newRecord);
            book.setAvailability("UnAvailable");
            return "Book Borrowed";
        }
    }
    public String returnBook(int bookId){
        BorrowRecord record = findActiveBorrowRecord(bookId);
        if (record == null) {
            return "Book is not currently borrowed";
        }
        record.setReturnDate(LocalDate.now());
        record.setStatus("Returned");
        bookService.getBookById(bookId).setAvailability("Available");

        return "Book Returned";
    }

}
