package com.camster_be.domain.study.dto.response;

import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.study.entity.Study;

public record StudyMemberListResponse(
        Long memberId,
        String nickname
) {
    public static StudyMemberListResponse of(Member member) {
        return new StudyMemberListResponse(
                member.getId(),
                member.getNickname()
        );
    }
}
