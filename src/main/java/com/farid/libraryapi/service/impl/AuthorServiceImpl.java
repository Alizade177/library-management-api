package com.farid.libraryapi.service.impl;

import com.farid.libraryapi.dto.request.AuthorRequest;
import com.farid.libraryapi.dto.response.AuthorResponse;
import com.farid.libraryapi.entity.Author;
import com.farid.libraryapi.exception.ResourceNotFoundException;
import com.farid.libraryapi.mapper.AuthorMapper;
import com.farid.libraryapi.repository.AuthorRepository;
import com.farid.libraryapi.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponse createAuthor(AuthorRequest request) {

        Author author = AuthorMapper.toEntity(request);
        Author savedAuthor = authorRepository.save(author);

        return AuthorMapper.toResponse(savedAuthor);
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::toResponse)
                .toList();
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        return AuthorMapper.toResponse(author);
    }

    @Override
    public AuthorResponse updateAuthor(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        author.setName(request.getName());
        author.setEmail(request.getEmail());

        Author updatedAuthor = authorRepository.save(author);

        return AuthorMapper.toResponse(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        authorRepository.delete(author);
    }
}
