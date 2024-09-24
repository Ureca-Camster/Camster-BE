package com.camster_be.domain.member.controller;

import com.camster_be.domain.member.dto.request.MemberUpdateRequest;
import com.camster_be.domain.member.dto.request.RegisterRequest;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        memberService.register(request);
    }

    @GetMapping("/check")
    public Boolean checkEmail(@RequestParam("email") String email) {
        return memberService.emailCheck(email);
    }

    @GetMapping
    public Member getMember() {
        return memberService.getMember();
    }

    @PutMapping
    public Member updateMember(@RequestBody MemberUpdateRequest request) {
        return memberService.updateMember(request);
    }

    @PutMapping("/updateTodayTime")
    public void updateTodayTime(@RequestBody Integer time) {
        memberService.updateTodayTime(time);
    }



}
