package com.camster_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.camster_be.entity.StudyLive;
import com.camster_be.service.StudyLiveService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studyLives")  // 기본 경로 설정
@CrossOrigin("*")  // 모든 도메인에서의 요청 허용
public class StudyLiveController {

    @Autowired
    private StudyLiveService studyLiveService;

    @PostMapping
    public StudyLive createStudyLive(@RequestBody StudyLive studyLive) {
        return studyLiveService.createStudyLive(studyLive);
    }

    @GetMapping
    public List<StudyLive> getAllStudyLives() {
        return studyLiveService.getAllStudyLives();
    }

    @GetMapping("/{id}")
    public Optional<StudyLive> getStudyLiveById(@PathVariable Long id) {
        return studyLiveService.getStudyLiveById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudyLive(@PathVariable Long id) {
        studyLiveService.deleteStudyLive(id);
    }
}




