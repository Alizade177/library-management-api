package com.farid.libraryapi.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private Long id;
    private String username;
    private String email;
    private String role;
    private String token;
}
