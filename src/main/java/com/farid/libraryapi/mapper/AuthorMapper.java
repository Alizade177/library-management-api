package com.farid.libraryapi.mapper;

import com.farid.libraryapi.dto.request.AuthorRequest;
import com.farid.libraryapi.dto.response.AuthorResponse;
import com.farid.libraryapi.entity.Author;
import com.sun.source.doctree.AuthorTree;

import java.util.Random;

public class AuthorMapper {

    public static Author toEntity(AuthorRequest request) {
        Author author = new Author();

        author.setName(request.getName());
        author.setEmail(request.getEmail());

        return  author;
    }

    public static AuthorResponse toResponse(Author author) {
        AuthorResponse response = new AuthorResponse();

        response.setId(author.getId());
        response.setName(author.getName());
        response.setEmail(author.getEmail());

        return response;
    }
}
