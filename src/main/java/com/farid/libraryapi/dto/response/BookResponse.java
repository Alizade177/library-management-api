package com.farid.libraryapi.dto.response;


import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class BookResponse {

    private Long id;

    private String title;

    private Double price;

    private String authorName;

    private String memberName;

    private Set<String> categories;
}
