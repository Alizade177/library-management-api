package com.farid.libraryapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private List<OrderItemRequest> items;

}