package com.camster_be.domain.member.dto.request;

public record RegisterRequest(
        String nickname,
        String email,
        String memberPassword,
        Integer goalTime
) {}
