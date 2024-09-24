package com.camster_be.domain.board.dto.request;

public record BoardUpdateRequest(
        String title,
        String content
) {
}
