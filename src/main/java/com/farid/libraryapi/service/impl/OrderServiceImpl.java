package com.farid.libraryapi.service.impl;

import com.farid.libraryapi.dto.request.OrderItemRequest;
import com.farid.libraryapi.dto.request.OrderRequest;
import com.farid.libraryapi.dto.response.OrderResponse;
import com.farid.libraryapi.entity.Book;
import com.farid.libraryapi.entity.Member;
import com.farid.libraryapi.entity.Order;
import com.farid.libraryapi.entity.OrderItem;
import com.farid.libraryapi.exception.ResourceNotFoundException;
import com.farid.libraryapi.mapper.OrderMapper;
import com.farid.libraryapi.repository.BookRepository;
import com.farid.libraryapi.repository.MemberRepository;
import com.farid.libraryapi.repository.OrderRepository;
import com.farid.libraryapi.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            MemberRepository memberRepository,
            BookRepository bookRepository) {

        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found"));

        Order order = new Order();

        order.setMember(member);
        order.setOrderDate(LocalDateTime.now());

        for (OrderItemRequest itemRequest : request.getItems()) {

            Book book = bookRepository.findById(itemRequest.getBookId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Book not found"));

            OrderItem item = new OrderItem();

            item.setBook(book);

            item.setQuantity(itemRequest.getQuantity());

            item.setPrice(book.getPrice());

            item.setOrder(order);

            order.getItems().add(item);
        }

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        return OrderMapper.toResponse(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        orderRepository.delete(order);
    }

    @Transactional
    public void createOrderWithError(OrderRequest request){

        Member member =
                memberRepository.findById(request.getMemberId())
                        .orElseThrow();

        Order order=new Order();

        order.setMember(member);

        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);

        throw new RuntimeException("Rollback test");
    }

}