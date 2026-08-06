package com.farid.libraryapi.repository;

import com.farid.libraryapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository
        extends JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthor_NameContainingIgnoreCase(String authorName);

    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Book> findByCategories_Name(String categoryName);

    @Query("""
            select b
            from Book b
            where lower(b.title)
            like lower(concat('%',:title,'%'))
            """)
    List<Book> searchBooksByTitle(String title);

    @Query("""
            select b
            from Book b
            where b.author.name=:author
            """)
    List<Book> findBooksByAuthor(String author);

    @Query("""
            select b
            from Book b
            where b.price>=:price
            """)
    List<Book> findExpensiveBooks(Double price);


    @Query(value = """
            
            select *
            
            from books
            
            where price>
            
            :minPrice
            
            """, nativeQuery = true)
    List<Book> findBooksNative(Double minPrice);

}