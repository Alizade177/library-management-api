package com.farid.libraryapi.mapper;

import com.farid.libraryapi.dto.request.CategoryRequest;
import com.farid.libraryapi.dto.response.CategoryResponse;
import com.farid.libraryapi.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest request){

        Category category = new Category();

        category.setName(request.getName());

        return category;
    }

    public static CategoryResponse toResponse(Category category){

        CategoryResponse response = new CategoryResponse();

        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }
}
