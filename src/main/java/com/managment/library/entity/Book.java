package com.managment.library.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {

    private String title;

    @Id
    @Column(unique = true, nullable = false)
    private String isbn;

    private String genre;

    private boolean status;
}
