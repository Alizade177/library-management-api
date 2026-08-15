package com.farid.libraryapi.service.impl;

import com.farid.libraryapi.dto.request.BookRequest;
import com.farid.libraryapi.dto.request.BookSearchRequest;
import com.farid.libraryapi.dto.response.BookResponse;
import com.farid.libraryapi.entity.Author;
import com.farid.libraryapi.entity.Book;
import com.farid.libraryapi.entity.Category;
import com.farid.libraryapi.entity.Member;
import com.farid.libraryapi.exception.ResourceNotFoundException;
import com.farid.libraryapi.mapper.BookMapper;
import com.farid.libraryapi.repository.AuthorRepository;
import com.farid.libraryapi.repository.BookRepository;
import com.farid.libraryapi.repository.CategoryRepository;
import com.farid.libraryapi.repository.MemberRepository;
import com.farid.libraryapi.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.farid.libraryapi.specification.BookSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           MemberRepository memberRepository, CategoryRepository categoryRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public BookResponse createBook(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Book book = BookMapper.toEntity(request);

        book.setAuthor(author);

        if (request.getMemberId() != null) {

            Member member = memberRepository.findById(request.getMemberId())
                    .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

            book.setMember(member);
        }

        Set<Category> categories =
                request.getCategoryIds()
                        .stream()
                        .map(id ->
                                categoryRepository.findById(id)
                                        .orElseThrow(() ->
                                                new ResourceNotFoundException(
                                                        "Category not found: " + id
                                                )
                                        )
                        )
                        .collect(Collectors.toSet());

        book.setCategories(categories);

        Book savedBook = bookRepository.save(book);

        return BookMapper.toResponse(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponse> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(BookMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return BookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setAuthor(author);

        if (request.getMemberId() != null) {

            Member member = memberRepository.findById(request.getMemberId())
                    .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

            book.setMember(member);
        } else {

            book.setMember(null);

        }

        Book updatedBook = bookRepository.save(book);

        return BookMapper.toResponse(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookRepository.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponse> searchBooks(
            String title,
            String author,
            String category,
            Double minPrice,
            Double maxPrice,
            Pageable pageable) {

        Specification<Book> specification =
                Specification.where(BookSpecification.hasTitle(title))
                        .and(BookSpecification.hasAuthor(author))
                        .and(BookSpecification.hasCategory(category))
                        .and(BookSpecification.minPrice(minPrice))
                        .and(BookSpecification.maxPrice(maxPrice));

        return bookRepository.findAll(specification, pageable)
                .map(BookMapper::toResponse);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<BookResponse> searchBooks(
            BookSearchRequest request,
            Pageable pageable) {

        Specification<Book> specification =
                (root, query, cb) -> null;

        if (request.getTitle() != null &&
                !request.getTitle().isBlank()) {

            specification = specification.and(
                    BookSpecification.hasTitle(request.getTitle())
            );
        }

        if (request.getAuthor() != null &&
                !request.getAuthor().isBlank()) {

            specification = specification.and(
                    BookSpecification.hasAuthor(request.getAuthor())
            );
        }

        if (request.getCategory() != null &&
                !request.getCategory().isBlank()) {

            specification = specification.and(
                    BookSpecification.hasCategory(request.getCategory())
            );
        }

        if (request.getMinPrice() != null) {

            specification = specification.and(
                    BookSpecification.minPrice(request.getMinPrice())
            );
        }

        if (request.getMaxPrice() != null) {

            specification = specification.and(
                    BookSpecification.maxPrice(request.getMaxPrice())
            );
        }

        return bookRepository.findAll(specification, pageable)
                .map(BookMapper::toResponse);
    }


}
