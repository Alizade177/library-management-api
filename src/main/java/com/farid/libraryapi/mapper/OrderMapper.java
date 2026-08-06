package com.farid.libraryapi.mapper;

import com.farid.libraryapi.dto.response.OrderItemResponse;
import com.farid.libraryapi.dto.response.OrderResponse;
import com.farid.libraryapi.entity.Order;
import com.farid.libraryapi.entity.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponse toResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setMemberName(order.getMember().getFullName());
        response.setOrderDate(order.getOrderDate());

        response.setItems(

                order.getItems()

                        .stream()

                        .map(OrderMapper::toItemResponse)

                        .collect(Collectors.toList())

        );

        response.setTotalPrice(

                order.getItems()

                        .stream()

                        .mapToDouble(item -> item.getPrice() * item.getQuantity())

                        .sum()

        );

        return response;
    }

    private static OrderItemResponse toItemResponse(OrderItem item){

        OrderItemResponse response = new OrderItemResponse();

        response.setBookTitle(item.getBook().getTitle());

        response.setQuantity(item.getQuantity());

        response.setPrice(item.getPrice());

        return response;
    }

}