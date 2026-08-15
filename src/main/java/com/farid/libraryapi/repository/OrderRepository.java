package com.farid.libraryapi.repository;

import com.farid.libraryapi.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {
            "member",
            "items",
            "items.book"
    })
    @Query("select o from Order o")
    List<Order> findAllWithEntityGraph();

    @EntityGraph(attributePaths = {
            "member",
            "items",
            "items.book"
    })
    @Query("select o from Order o where o.id = :id")
    Optional<Order> findByIdWithEntityGraph(@Param("id") Long id);
}