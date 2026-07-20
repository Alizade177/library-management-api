package com.farid.libraryapi.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {

    private Long id;

    private String title;

    private Double price;

    private String authorName;

    private String memberName;
}
