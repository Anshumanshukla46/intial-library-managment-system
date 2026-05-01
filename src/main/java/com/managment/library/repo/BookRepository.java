package com.managment.library.repo;

import com.managment.library.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("select b from Book b")
    List<Book> findAllBooks();

    @Query("select b from Book b where b.status = false")
    List<Book> findAllBorrowedBooks();

    @Query("select b from Book b where b.status = true")
    List<Book> findAllAvailableBooks();

    @Modifying
    @Query("delete from Book b where b.isbn = :isbn")
    int deleteByIsbn(@Param("isbn") String isbn);

    @Modifying
    @Query("update Book b set b.status = case when b.status = true then false else true end where b.isbn = :id")
    int borrowOrReturnBookById(@Param("id") String isbn);

    @Query("select b.status from Book b where b.isbn = :id")
    Optional<Boolean> getStatusById(@Param("id") String isbn);
}
