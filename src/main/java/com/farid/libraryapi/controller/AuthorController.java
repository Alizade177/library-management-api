package com.farid.libraryapi.controller;

import com.farid.libraryapi.dto.request.AuthorRequest;
import com.farid.libraryapi.dto.response.AuthorResponse;
import com.farid.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request){

        AuthorResponse authorResponse = authorService.createAuthor(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponse);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(
            @PathVariable Long id,@Valid @RequestBody AuthorRequest request){
        return ResponseEntity.ok(authorService.updateAuthor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }


}
