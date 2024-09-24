package com.camster_be.domain.member.service;

import com.camster_be.domain.member.dto.request.MemberUpdateRequest;
import com.camster_be.domain.member.dto.request.RegisterRequest;
import com.camster_be.domain.member.entity.Member;

public interface MemberService {

    Member register(RegisterRequest request);

    Boolean emailCheck(String email);

    Member getMember();

    Member updateMember(MemberUpdateRequest request);

    void updateTodayTime(Integer todayTime);

}
