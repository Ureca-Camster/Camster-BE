package com.camster_be.domain.study.dto.request;

public record StudyUpdateRequest(
        String studyName,
        String description,
        String emoji,
        Boolean isPublic,
        String studyPassword
) {}
