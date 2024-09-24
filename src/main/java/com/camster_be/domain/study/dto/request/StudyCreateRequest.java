package com.camster_be.domain.study.dto.request;

public record StudyCreateRequest(
        String studyName,
        String description,
        String emoji,
        Boolean isPublic,
        String studyPassword
) {}
