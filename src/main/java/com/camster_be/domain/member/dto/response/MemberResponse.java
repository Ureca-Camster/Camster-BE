package com.camster_be.domain.member.dto.response;

import com.camster_be.domain.member.entity.Member;

public record MemberResponse(
        Long memberId,
        String nickname,
        String email,
        Integer goalTime,
        Integer todayTime
) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getGoalTime(),
                member.getTodayTime()
        );
    }
}