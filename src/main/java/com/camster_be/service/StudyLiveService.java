package com.camster_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camster_be.entity.StudyLive;
import com.camster_be.repository.StudyLiveRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StudyLiveService {

    @Autowired
    private StudyLiveRepository studyLiveRepository;

    public StudyLive createStudyLive(StudyLive studyLive) {
        return studyLiveRepository.save(studyLive);
    }

    public List<StudyLive> getAllStudyLives() {
        return studyLiveRepository.findAll();
    }

    public Optional<StudyLive> getStudyLiveById(Long id) {
        return studyLiveRepository.findById(id);
    }

    public void deleteStudyLive(Long id) {
        studyLiveRepository.deleteById(id);
    }
}



