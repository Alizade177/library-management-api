package com.farid.libraryapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {

    private String bookTitle;

    private Integer quantity;

    private Double price;

}