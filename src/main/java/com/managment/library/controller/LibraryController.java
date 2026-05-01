package com.managment.library.controller;

import com.managment.library.entity.Book;
import com.managment.library.service.BookInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@AllArgsConstructor
public class LibraryController {

    private BookInventoryService bookInventoryService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookInventoryService.getAllBooks());
    }

    @GetMapping("/books/borrow")
    public ResponseEntity<List<Book>> getListOfAllBorrowedBooks() {
        return ResponseEntity.ok(bookInventoryService.getListOfAllBorrowedBooks());
    }

    @GetMapping("/books/available")
    public ResponseEntity<List<Book>> getListOfAllAvailableBooks() {
        return ResponseEntity.ok(bookInventoryService.getListOfAllAvailableBooks());
    }

    @PostMapping("/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        boolean status = bookInventoryService.exists(book);
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookInventoryService.saveBook(book));
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String isbn) {
        return ResponseEntity.status(HttpStatus.OK).body(bookInventoryService.deleteBookByIsbn(isbn));
    }

    @PatchMapping("/books/available")
    public ResponseEntity<String> returnBookById(@RequestParam("id") String isbn) {
        return ResponseEntity.ok(bookInventoryService.returnBookById(isbn));
    }

    @PutMapping("/books/borrow")
    public ResponseEntity<String> borrowBookById(@RequestParam("id") String isbn){
        return ResponseEntity.ok(bookInventoryService.borrowBookById(isbn));
    }
}
