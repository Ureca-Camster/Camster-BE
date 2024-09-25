package com.camster_be.domain.member.service;

import com.camster_be.domain.member.dto.request.MemberUpdateRequest;
import com.camster_be.domain.member.dto.request.RegisterRequest;
import com.camster_be.domain.member.dto.response.MemberResponse;

public interface MemberService {

    void register(RegisterRequest request);

    Boolean emailCheck(String email);

    MemberResponse getMember();

    MemberResponse updateMember(MemberUpdateRequest request);

    void updateTodayTime(Integer todayTime);

}
