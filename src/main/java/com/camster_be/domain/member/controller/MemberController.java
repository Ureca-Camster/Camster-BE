package com.camster_be.domain.member.controller;

import com.camster_be.domain.member.dto.request.MemberUpdateRequest;
import com.camster_be.domain.member.dto.request.RegisterRequest;
import com.camster_be.domain.member.dto.response.MemberResponse;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
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
    public MemberResponse getMember() {
        return memberService.getMember();
    }

    @GetMapping("/time")
    public Integer getTodayTime() {
        return memberService.getTodayTime();
    }

    @PutMapping
    public MemberResponse updateMember(@RequestBody MemberUpdateRequest request) {
        return memberService.updateMember(request);
    }

    @PutMapping("/updateTodayTime")
    public void updateTodayTime(@RequestBody Integer time) {
        log.info("updateTodayTime: " + time);
        memberService.updateTodayTime(time);
    }



}
