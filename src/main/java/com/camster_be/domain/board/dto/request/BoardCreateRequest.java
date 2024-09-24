package com.camster_be.domain.board.dto.request;

public record BoardCreateRequest(
        String title,
        String content
) {
}
