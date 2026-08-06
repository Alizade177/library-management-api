package com.farid.libraryapi.controller;


import com.farid.libraryapi.dto.request.BookRequest;
import com.farid.libraryapi.dto.response.BookResponse;
import com.farid.libraryapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody BookRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createBook(request));
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {

        return ResponseEntity.ok(bookService.updateBook(id, request));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<Page<BookResponse>> searchBooks(

            @RequestParam(required = false) String title,

            @RequestParam(required = false) String author,

            @RequestParam(required = false) String category,

            @RequestParam(required = false) Double minPrice,

            @RequestParam(required = false) Double maxPrice,

            Pageable pageable) {

        return ResponseEntity.ok(

                bookService.searchBooks(
                        title,
                        author,
                        category,
                        minPrice,
                        maxPrice,
                        pageable
                )

        );
    }
}
