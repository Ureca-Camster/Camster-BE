package com.camster_be.domain.study.controller;

import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import com.camster_be.domain.study.dto.response.MyStudyResponse;
import com.camster_be.domain.study.dto.response.NotMyStudyResponse;
import com.camster_be.domain.study.dto.response.StudyDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.camster_be.domain.study.service.StudyService;
import java.util.List;

@RestController
@RequestMapping("/studies")  // 기본 경로 설정
@CrossOrigin("*")  // 모든 도메인에서의 요청 허용
public class StudyController {

    @Autowired
    private StudyService studyService;

    // 스터디 생성
    @PostMapping
    public Long createStudy(@RequestBody StudyCreateRequest request) {
        return studyService.createStudy(request);
    }

    // 전체 스터디 목록 조회
    @GetMapping
    public List<NotMyStudyResponse> getAllStudies() {
        return studyService.getAllStudies();
    }

    // 내 스터디 목록 조회 (예시: 특정 사용자의 스터디 목록)
    @GetMapping("/mystudies")
    public List<MyStudyResponse> getMyStudies() {
        return studyService.getMyStudies();
    }

    // 상세조회
    @GetMapping("/{studyId}")
    public StudyDetailResponse getStudyById(@PathVariable("studyId") Long studyId) {
        return studyService.getStudyById(studyId);
    }

    // 스터디 수정
    @PutMapping("/{studyId}")
    public void updateStudy(@PathVariable Long studyId, @RequestBody StudyUpdateRequest request) {
        studyService.updateStudy(studyId, request);
    }

    // 스터디 탈퇴
    @DeleteMapping("/{studyId}")
    public void deleteStudy(@PathVariable Long studyId) {
        studyService.deleteStudy(studyId);
    }

    // 스터디 가입
    @PostMapping("/{studyId}")
    public void joinStudy(@PathVariable Long studyId) {
        studyService.joinStudy(studyId);
    }

}
