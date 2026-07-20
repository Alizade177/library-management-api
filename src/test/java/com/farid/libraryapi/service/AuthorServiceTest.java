package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.AuthorRequest;
import com.farid.libraryapi.dto.response.AuthorResponse;
import com.farid.libraryapi.entity.Author;
import com.farid.libraryapi.repository.AuthorRepository;
import com.farid.libraryapi.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    private AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        authorService = new AuthorServiceImpl(authorRepository);

    }

    @Test
    void createAuthor_shouldReturnSavedAuthor() {

        AuthorRequest request = new AuthorRequest();

        request.setName("Farid");
        request.setEmail("farid@gmail.com");

        Author author = new Author();

        author.setId(1L);
        author.setName("Farid");
        author.setEmail("farid@gmail.com");

        when(authorRepository.save(any(Author.class)))
                .thenReturn(author);

        AuthorResponse response = authorService.createAuthor(request);

        assertNotNull(response);

        assertEquals("Farid", response.getName());

        assertEquals("farid@gmail.com", response.getEmail());

    }

    @Test
    void deleteAuthor_shouldCallRepository() {

        Author author = new Author();

        author.setId(1L);

        when(authorRepository.findById(1L))
                .thenReturn(java.util.Optional.of(author));

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1))
                .delete(author);

    }
}
