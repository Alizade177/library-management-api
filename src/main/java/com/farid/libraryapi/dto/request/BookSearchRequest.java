package com.farid.libraryapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchRequest {
    private String title;

    private String author;

    private String category;

    private Double minPrice;

    private Double maxPrice;
}
