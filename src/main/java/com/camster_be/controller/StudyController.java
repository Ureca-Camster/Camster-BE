package com.camster_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.camster_be.entity.Study;
import com.camster_be.service.StudyService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studies")  // 기본 경로 설정
@CrossOrigin("*")  // 모든 도메인에서의 요청 허용
public class StudyController {

    @Autowired
    private StudyService studyService;

    // 스터디 생성
    @PostMapping
    public Study createStudy(@RequestBody Study study) {
        return studyService.createStudy(study);
    }

    // 전체 스터디 목록 조회
    @GetMapping
    public List<Study> getAllStudies() {
        return studyService.getAllStudies();
    }

    // 내 스터디 목록 조회 (예시: 특정 사용자의 스터디 목록)
    @GetMapping("/mystudies")
    public List<Study> getMyStudies(@RequestParam Long memberId) {
        return studyService.getMyStudies(memberId);
    }

    @GetMapping("/{studyId}")
    public Optional<Study> getStudyById(@PathVariable("studyId") Long studyId) {
        return studyService.getStudyById(studyId);
    }


    // 스터디 수정
    @PutMapping("/{studyId}")
    public Study updateStudy(@PathVariable Long studyId, @RequestBody Study study) {
        return studyService.updateStudy(studyId, study);
    }

    // 스터디 탈퇴
    @DeleteMapping("/{studyId}")
    public void deleteStudy(@PathVariable Long studyId) {
        studyService.deleteStudy(studyId);
    }

    // 스터디 가입
    @PostMapping("/{studyId}")
    public Study joinStudy(@PathVariable Long studyId, @RequestBody Long memberId) {
        return studyService.joinStudy(studyId, memberId);
    }
}
