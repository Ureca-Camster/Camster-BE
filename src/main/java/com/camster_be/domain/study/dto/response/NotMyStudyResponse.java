package com.camster_be.domain.study.dto.response;

import com.camster_be.domain.study.entity.Study;

public record NotMyStudyResponse(
        Long studyId,
        String studyName,
        String description,
        String emoji,
        Boolean isPublic
) {
    public static NotMyStudyResponse of(Study study) {
        return new NotMyStudyResponse(
                study.getId(),
                study.getStudyName(),
                study.getDescription(),
                study.getEmoji(),
                study.getIsPublic()
        );
    }
}
