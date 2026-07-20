package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.AuthorRequest;
import com.farid.libraryapi.dto.response.AuthorResponse;

import java.util.List;

public interface AuthorService {

    AuthorResponse createAuthor(AuthorRequest request);

    List<AuthorResponse> getAllAuthors();

    AuthorResponse getAuthorById(Long id);

    AuthorResponse updateAuthor(Long id, AuthorRequest request);

    void deleteAuthor(Long id);
}
