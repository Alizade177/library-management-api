package com.farid.libraryapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long id;

    private String memberName;

    private LocalDateTime orderDate;

    private Double totalPrice;

    private List<OrderItemResponse> items;

}