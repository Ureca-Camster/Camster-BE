package com.camster_be.domain.auth.service;

import com.camster_be.domain.auth.dto.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void login(LoginRequest loginRequest, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

    void reissue(HttpServletRequest request, HttpServletResponse response);

}
