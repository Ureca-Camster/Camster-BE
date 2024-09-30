package com.camster_be.domain.member.service;

import com.camster_be.domain.member.dto.request.MemberUpdateRequest;
import com.camster_be.domain.member.dto.request.RegisterRequest;
import com.camster_be.domain.member.dto.response.MemberResponse;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.repository.MemberRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.camster_be.domain.util.SecurityUtils;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        String encodePassword = passwordEncoder.encode(request.memberPassword());
        Member member = new Member(request.email(), request.nickname(), request.goalTime(), encodePassword);
        memberRepository.save(member);
    }

    @Override
    public Boolean emailCheck(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Override
    public MemberResponse getMember() {
        Long memberId = SecurityUtils.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElse(null);
        return MemberResponse.of(findMember);
    }

    @Override
    public MemberResponse updateMember(MemberUpdateRequest request) {
        Long memberId = SecurityUtils.getMemberId();
        Member member = memberRepository.findById(memberId).orElse(null);

        String passwordToUpdate;
        if (StringUtils.isBlank(request.memberPassword())) {
            passwordToUpdate = null;  // or "" if you prefer
        } else {
            passwordToUpdate = passwordEncoder.encode(request.memberPassword());
        }

        member.updateMember(request.nickname(), passwordToUpdate, request.goalTime());
        return MemberResponse.of(member);
    }

    @Override
    public void updateTodayTime(Integer todayTime) {
        Long memberId = SecurityUtils.getMemberId();
        Member member = memberRepository.findById(memberId).orElse(null);
        member.updateTodayTime(todayTime);
    }

    @Override
    public Integer getTodayTime() {
        Long memberId = SecurityUtils.getMemberId();
        Member member = memberRepository.findById(memberId).orElse(null);
        return member.getTodayTime();
    }

}
