package com.farid.libraryapi.mapper;

import com.farid.libraryapi.dto.request.BookRequest;
import com.farid.libraryapi.dto.response.BookResponse;
import com.farid.libraryapi.entity.Book;

public class BookMapper {
    public static Book toEntity(BookRequest request) {

        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());

        return book;
    }

    public static BookResponse toResponse(Book book) {
        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setPrice(book.getPrice());

        response.setAuthorName(book.getAuthor().getName());

        if (book.getMember() != null) {
            response.setMemberName(book.getMember().getFullName());
        }

        return response;
    }
}
