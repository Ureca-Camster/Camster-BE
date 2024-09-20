package com.camster_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.camster_be.entity.StudyMember;
import com.camster_be.service.StudyMemberService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studyMembers")  // 기본 경로 설정
@CrossOrigin("*")  // 모든 도메인에서의 요청 허용
public class StudyMemberController {

    @Autowired
    private StudyMemberService studyMemberService;

    @PostMapping
    public StudyMember createStudyMember(@RequestBody StudyMember studyMember) {
        return studyMemberService.createStudyMember(studyMember);
    }

    @GetMapping
    public List<StudyMember> getAllStudyMembers() {
        return studyMemberService.getAllStudyMembers();
    }

    @GetMapping("/{id}")
    public Optional<StudyMember> getStudyMemberById(@PathVariable Long id) {
        return studyMemberService.getStudyMemberById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudyMember(@PathVariable Long id) {
        studyMemberService.deleteStudyMember(id);
    }
}




