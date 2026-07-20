package com.farid.libraryapi.repository;

import com.farid.libraryapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long>{

}
