package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.BookRequest;
import com.farid.libraryapi.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.farid.libraryapi.dto.request.BookSearchRequest;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookRequest request);

    Page<BookResponse> getAllBooks(Pageable pageable);

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id,BookRequest request);

    void deleteBook(Long id);

    Page<BookResponse> searchBooks(
            String title,
            String author,
            String category,
            Double minPrice,
            Double maxPrice,
            Pageable pageable
    );

    //List<BookResponse> searchBooks(BookSearchRequest request);

    Page<BookResponse> searchBooks(
            BookSearchRequest request,
            Pageable pageable
    );
}
