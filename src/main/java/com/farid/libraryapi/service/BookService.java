package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.BookRequest;
import com.farid.libraryapi.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookRequest request);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id,BookRequest request);

    void deleteBook(Long id);
}
