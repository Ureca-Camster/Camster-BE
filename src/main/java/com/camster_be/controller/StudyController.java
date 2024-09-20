package com.camster_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.camster_be.entity.Study;
import com.camster_be.service.StudyService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studies")  // 기본 경로 설정
@CrossOrigin("*")  // 모든 도메인에서의 요청 허용
public class StudyController {

    @Autowired
    private StudyService studyService;

    @PostMapping
    public Study createStudy(@RequestBody Study study) {
        return studyService.createStudy(study);
    }

    @GetMapping
    public List<Study> getAllStudies() {
        return studyService.getAllStudies();
    }

    @GetMapping("/{id}")
    public Optional<Study> getStudyById(@PathVariable Long id) {
        return studyService.getStudyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudy(@PathVariable Long id) {
        studyService.deleteStudy(id);
    }
}



