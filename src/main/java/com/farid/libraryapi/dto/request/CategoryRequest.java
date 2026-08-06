package com.farid.libraryapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;
}
