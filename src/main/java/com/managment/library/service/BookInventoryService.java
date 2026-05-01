package com.managment.library.service;

import com.managment.library.entity.Book;
import com.managment.library.repo.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookInventoryService {

    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public List<Book> getListOfAllBorrowedBooks() {
        return bookRepository.findAllBorrowedBooks();
    }

    public List<Book> getListOfAllAvailableBooks() {
        return bookRepository.findAllAvailableBooks();
    }

    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public boolean exists(Book book){
        return bookRepository.existsById(book.getIsbn());
    }

    @Transactional
    public String deleteBookByIsbn(String isbn) {
        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteByIsbn(isbn);
            return "Book Deleted of id: " + isbn;
        } else {
            return "Book with id: " + isbn + " doesn't even exist";
        }
    }

    @Transactional
    public String returnBookById(String isbn) {
        int updated = bookRepository.borrowOrReturnBookById(isbn);
        if (updated != 0) {
            return "Book returned";
        }
        return "Book isn't returned yet. Try Again...";
    }

    @Transactional
    public String borrowBookById(String isbn) {
        Optional<Boolean> isAvail = bookRepository.getStatusById(isbn);
        if (isAvail.isPresent() && isAvail.get()) {
            int updated = bookRepository.borrowOrReturnBookById(isbn);
            if (updated != 0) {
                return "Book Taken";
            }
        }

        return "Book isn't available. Try someday";
    }

}
