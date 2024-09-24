package com.camster_be.domain.study.service;

import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import com.camster_be.domain.study.entity.Study;

import java.util.List;
import java.util.Optional;

public interface StudyService {

    Study createStudy(StudyCreateRequest request);

    List<Study> getAllStudies();

    List<Study> getMyStudies();

    Optional<Study> getStudyById(Long studyId);

    Study updateStudy(Long studyId, StudyUpdateRequest request);

    void deleteStudy(Long studyId);

    void joinStudy(Long studyId);
}
