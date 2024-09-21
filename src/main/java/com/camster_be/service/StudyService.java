package com.camster_be.service;

import com.camster_be.entity.Study;
import com.camster_be.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyService {

    @Autowired
    private StudyRepository studyRepository;

    // 스터디 생성
    public Study createStudy(Study study) {
        return studyRepository.save(study);
    }

    // 전체 스터디 목록 조회
    public List<Study> getAllStudies() {
        return studyRepository.findAll();
    }

    // 내 스터디 목록 조회 (사용자 ID로 스터디 조회)
    public List<Study> getMyStudies(Long memberId) {
        return studyRepository.findByMemberId(memberId);
    }

    // 스터디 상세 조회
    public Optional<Study> getStudyById(Long studyId) {
        return studyRepository.findById(studyId);
    }

    // 스터디 수정
    public Study updateStudy(Long studyId, Study study) {
        Optional<Study> existingStudy = studyRepository.findById(studyId);
        if (existingStudy.isPresent()) {
            Study updatedStudy = existingStudy.get();
            updatedStudy.setStudyName(study.getStudyName());  // 수정할 필드를 설정
            updatedStudy.setDescription(study.getDescription());
            return studyRepository.save(updatedStudy);
        }
        return null;  // 예외 처리 추가 가능
    }

    // 스터디 삭제
    public void deleteStudy(Long studyId) {
        studyRepository.deleteById(studyId);
    }

    // 스터디 가입 (사용자 ID를 추가하는 방식)
    public Study joinStudy(Long studyId, Long memberId) {
        Optional<Study> study = studyRepository.findById(studyId);
        if (study.isPresent()) {
            Study existingStudy = study.get();
            // memberId를 해당 스터디에 추가하는 로직을 구현
            return studyRepository.save(existingStudy);
        }
        return null;  // 예외 처리 추가 가능
    }
}
