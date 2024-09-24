package com.camster_be.domain.member.service;

import com.camster_be.domain.member.dto.request.MemberUpdateRequest;
import com.camster_be.domain.member.dto.request.RegisterRequest;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.repository.MemberRepository;
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
    public Member register(RegisterRequest request) {
        String encodePassword = passwordEncoder.encode(request.memberPassword());
        Member member = new Member(request.email(), request.nickname(), request.goalTime(), encodePassword);
        memberRepository.save(member);
        return null;
    }

    @Override
    public Boolean emailCheck(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Override
    public Member getMember() {
        Long memberId = SecurityUtils.getMemberId();

        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member updateMember(MemberUpdateRequest request) {
        Long memberId = SecurityUtils.getMemberId();

        Member member = memberRepository.findById(memberId).orElse(null);
        String encodePassword = passwordEncoder.encode(request.memberPassword());
        member.updateMember(request.nickname(), encodePassword, request.goalTime());
        return member;
    }

    @Override
    public void updateTodayTime(Integer todayTime) {
        Long memberId = SecurityUtils.getMemberId();

        Member member = memberRepository.findById(memberId).orElse(null);
        member.updateTodayTime(todayTime);
    }

}
