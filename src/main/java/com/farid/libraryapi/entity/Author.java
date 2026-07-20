package com.farid.libraryapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
