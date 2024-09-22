package com.camster_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.camster_be.entity.StudyMember;
import com.camster_be.service.StudyMemberService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studies/{studyId}/members")  // 스터디에 대한 멤버 API 경로 설정
@CrossOrigin(origins = "*")  // 모든 도메인에서의 요청 허용
public class StudyMemberController {

    @Autowired
    private StudyMemberService studyMemberService;

    // 스터디에 멤버 추가
    @PostMapping
    public StudyMember createStudyMember(@PathVariable("studyId") Long studyId, @RequestBody StudyMember studyMember) {
        studyMember.setStudyId(studyId);  // 스터디 ID 설정
        return studyMemberService.createStudyMember(studyMember);
    }

    // 해당 스터디의 모든 멤버 조회
    @GetMapping
    public List<StudyMember> getAllStudyMembers(@PathVariable("studyId") Long studyId) {
        return studyMemberService.getStudyMembersByStudyId(studyId);  // 특정 스터디의 멤버 조회
    }

    // 특정 멤버 조회
    @GetMapping("/{memberId}")
    public Optional<StudyMember> getStudyMemberById(@PathVariable("studyId") Long studyId, @PathVariable("memberId") Long memberId) {
        return studyMemberService.getStudyMemberByIdAndStudyId(memberId, studyId);  // 스터디 내 특정 멤버 조회
    }

    // 스터디에서 멤버 삭제
    @DeleteMapping("/{memberId}")
    public void deleteStudyMember(@PathVariable("studyId") Long studyId, @PathVariable("memberId") Long memberId) {
        studyMemberService.deleteStudyMember(memberId, studyId);  // 특정 멤버 삭제
    }
}
