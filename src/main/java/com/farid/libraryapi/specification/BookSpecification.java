package com.farid.libraryapi.specification;

import com.farid.libraryapi.entity.Book;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasTitle(String title){

        return (root, query, cb)->

                title == null ?

                        null :

                        cb.like(

                                cb.lower(root.get("title")),

                                "%" + title.toLowerCase() + "%"

                        );

    }

    public static Specification<Book> hasAuthor(String author){

        return (root, query, cb)->{

            if(author == null){

                return null;

            }

            Join<Object,Object> join =
                    root.join("author");

            return cb.like(

                    cb.lower(join.get("name")),

                    "%" + author.toLowerCase() + "%"

            );

        };

    }

    public static Specification<Book> hasCategory(String category){

        return (root, query, cb)->{

            if(category == null){

                return null;

            }

            Join<Object,Object> join =
                    root.join("categories");

            return cb.like(

                    cb.lower(join.get("name")),

                    "%" + category.toLowerCase() + "%"

            );

        };

    }

    public static Specification<Book> minPrice(Double minPrice){

        return (root, query, cb)->

                minPrice == null ?

                        null :

                        cb.greaterThanOrEqualTo(

                                root.get("price"),

                                minPrice

                        );

    }

    public static Specification<Book> maxPrice(Double maxPrice){

        return (root, query, cb)->

                maxPrice == null ?

                        null :

                        cb.lessThanOrEqualTo(

                                root.get("price"),

                                maxPrice

                        );

    }

}