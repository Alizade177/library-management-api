package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.OrderRequest;
import com.farid.libraryapi.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    void deleteOrder(Long id);

}