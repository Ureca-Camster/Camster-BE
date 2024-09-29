package com.camster_be.domain.study.dto.response;

import com.camster_be.domain.study.entity.Study;

import java.util.List;

public record StudyDetailResponse(
        Long studyId,
        String studyName,
        String description,
        String emoji,
        Boolean isPublic,
        String studyPassword,
        Long leaderId,
        List<StudyMemberListResponse> members
) {
    public static StudyDetailResponse of(Study study, List<StudyMemberListResponse> members) {
        return new StudyDetailResponse(
                study.getId(),
                study.getStudyName(),
                study.getDescription(),
                study.getEmoji(),
                study.getIsPublic(),
                study.getStudyPassword(),
                study.getLeaderId(),
                members
        );
    }
}
