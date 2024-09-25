package com.camster_be.domain.auth.dto.response;

import com.camster_be.domain.member.entity.Member;

public record LoginResponse(
        Long memberId,
        String nickname,
        String email,
        Integer goalTime,
        Integer todayTime
) {
    public static LoginResponse of(Member member) {
        return new LoginResponse(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getGoalTime(),
                member.getTodayTime()
        );
    }
}