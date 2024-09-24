package com.camster_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.camster_be.entity.Member;
import com.camster_be.service.MemberService;
import com.camster_be.dto.LoginRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")  // 기본 경로 설정
@CrossOrigin("*")  // 모든 도메인에서의 요청 허용
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Optional<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Member> memberOpt = memberService.findByEmail(loginRequest.getEmail());

        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            
            // 비밀번호 체크 (단순 비교 예시, 보안 강화 필요)
            if (member.getMemberPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(member);  // 로그인 성공 시 사용자 정보 반환
            } else {
                return ResponseEntity.status(401).body("비밀번호가 일치하지 않습니다.");
            }
        } else {
            return ResponseEntity.status(404).body("해당 이메일로 등록된 사용자가 없습니다.");
        }
    }
}
