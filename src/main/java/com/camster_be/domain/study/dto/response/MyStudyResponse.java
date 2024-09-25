package com.camster_be.domain.study.dto.response;

import com.camster_be.domain.study.entity.Study;

public record MyStudyResponse(
        Long studyId,
        String studyName,
        String description,
        String emoji
) {
    public static MyStudyResponse of(Study study) {
        return new MyStudyResponse(
                study.getId(),
                study.getStudyName(),
                study.getDescription(),
                study.getEmoji()
        );
    }
}
