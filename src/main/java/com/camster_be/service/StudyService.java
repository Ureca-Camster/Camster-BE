package com.camster_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camster_be.entity.Study;
import com.camster_be.repository.StudyRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StudyService {

    @Autowired
    private StudyRepository studyRepository;

    public Study createStudy(Study study) {
        return studyRepository.save(study);
    }

    public List<Study> getAllStudies() {
        return studyRepository.findAll();
    }

    public Optional<Study> getStudyById(Long id) {
        return studyRepository.findById(id);
    }

    public void deleteStudy(Long id) {
        studyRepository.deleteById(id);
    }
}

