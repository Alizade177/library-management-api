package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.OrderRequest;
import com.farid.libraryapi.entity.Member;
import com.farid.libraryapi.repository.MemberRepository;
import com.farid.libraryapi.repository.OrderRepository;
import com.farid.libraryapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Long memberId;

    @BeforeEach
    void setUp() {

        Member member = new Member();

        member.setFullName("Test Member");
        member.setPhone("0555555555");

        member = memberRepository.save(member);

        memberId = member.getId();
    }

    @Test
    void rollbackShouldWork() {

        long beforeCount = orderRepository.count();

        OrderRequest request = new OrderRequest();
        request.setMemberId(memberId);

        assertThrows(
                RuntimeException.class,
                () -> orderService.createOrderWithError(request)
        );

        long afterCount = orderRepository.count();

        assertEquals(
                beforeCount,
                afterCount
        );
    }
}