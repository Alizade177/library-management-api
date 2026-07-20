package com.farid.libraryapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
