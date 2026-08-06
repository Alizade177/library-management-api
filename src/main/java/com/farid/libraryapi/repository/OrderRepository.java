package com.farid.libraryapi.repository;

import com.farid.libraryapi.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {
            "member",
            "items",
            "items.book"
    })
    List<Order> findAllWithEntityGraph();

    @Query("""
        select distinct o
        from Order o
        join fetch o.member
        join fetch o.items i
        join fetch i.book
        """)
    List<Order> findAllWithItems();
}