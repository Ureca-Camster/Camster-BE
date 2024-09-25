package com.camster_be.domain.auth.service;

import com.camster_be.domain.auth.dto.request.LoginRequest;
import com.camster_be.domain.auth.dto.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

    void reissue(HttpServletRequest request, HttpServletResponse response);

}
