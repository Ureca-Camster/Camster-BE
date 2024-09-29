package com.camster_be.domain.study.controller;

import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyJoinRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import com.camster_be.domain.study.dto.response.MyStudyResponse;
import com.camster_be.domain.study.dto.response.NotMyStudyResponse;
import com.camster_be.domain.study.dto.response.StudyDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.camster_be.domain.study.service.StudyService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/studies")
@CrossOrigin("*")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @PostMapping
    public ResponseEntity<Long> createStudy(@RequestBody StudyCreateRequest request) {
        Long studyId = studyService.createStudy(request);

        log.info("create requests {}", request);

        return ResponseEntity.status(HttpStatus.OK).body(studyId);
    }

    @GetMapping
    public ResponseEntity<List<NotMyStudyResponse>> getAllStudies() {
        List<NotMyStudyResponse> studies = studyService.getAllStudies();

        log.info("all study : {}" , studies.toString());

        return ResponseEntity.ok(studies);
    }

    @GetMapping("/mystudies")
    public ResponseEntity<List<MyStudyResponse>> getMyStudies() {
        List<MyStudyResponse> myStudies = studyService.getMyStudies();

        log.info("my study : {} ", myStudies.toString());

        return ResponseEntity.ok(myStudies);
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<StudyDetailResponse> getStudyById(@PathVariable("studyId") Long studyId) {
        StudyDetailResponse study = studyService.getStudyById(studyId);
        return ResponseEntity.ok(study);
    }

    @PutMapping("/{studyId}")
    public ResponseEntity<Void> updateStudy(@PathVariable Long studyId, @RequestBody StudyUpdateRequest request) {
        studyService.updateStudy(studyId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{studyId}")
    public ResponseEntity<Void> deleteStudy(@PathVariable Long studyId) {
        studyService.deleteStudy(studyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studyId}")
    public ResponseEntity<Void> joinStudy(@PathVariable Long studyId, @RequestBody StudyJoinRequest studyJoinRequest) {
        log.info("join study : {} ", studyJoinRequest.studyPassword());
        studyService.joinStudy(studyId, studyJoinRequest.studyPassword());
        return ResponseEntity.ok().build();
    }
}