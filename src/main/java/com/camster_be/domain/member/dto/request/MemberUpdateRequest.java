package com.camster_be.domain.member.dto.request;

public record MemberUpdateRequest(
        String nickname,
        String memberPassword,
        Integer goalTime
) {}
