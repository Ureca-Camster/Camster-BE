package com.camster_be.domain.auth.dto.request;

public record LoginRequest(
        String email,
        String memberPassword
) {
}
