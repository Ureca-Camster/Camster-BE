package com.camster_be.service;

import com.camster_be.entity.Member;
import com.camster_be.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    // 이메일로 사용자 찾기
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
