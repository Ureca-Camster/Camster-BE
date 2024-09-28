package com.camster_be.domain.study.service;

import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import com.camster_be.domain.study.dto.response.MyStudyResponse;
import com.camster_be.domain.study.dto.response.NotMyStudyResponse;
import com.camster_be.domain.study.dto.response.StudyDetailResponse;

import java.util.List;

public interface StudyService {

    Long createStudy(StudyCreateRequest request);

    List<NotMyStudyResponse> getAllStudies();

    List<MyStudyResponse> getMyStudies();

    StudyDetailResponse getStudyById(Long studyId);

    void updateStudy(Long studyId, StudyUpdateRequest request);

    void deleteStudy(Long studyId);

    void joinStudy(Long studyId);
}
