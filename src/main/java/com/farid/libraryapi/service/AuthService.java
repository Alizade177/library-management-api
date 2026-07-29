package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.LoginRequest;
import com.farid.libraryapi.dto.request.RegisterRequest;
import com.farid.libraryapi.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
