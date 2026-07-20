package com.farid.libraryapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {

    private Long id;

    private String fullName;

    private String phone;
}
