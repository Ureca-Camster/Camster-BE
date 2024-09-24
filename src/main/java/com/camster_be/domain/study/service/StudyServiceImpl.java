package com.camster_be.domain.study.service;

import com.camster_be.domain.member.repository.MemberRepository;
import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import com.camster_be.domain.study.entity.Study;
import com.camster_be.domain.study.entity.StudyMember;
import com.camster_be.domain.study.repository.StudyMemberRepository;
import com.camster_be.domain.study.repository.StudyRepository;
import com.camster_be.domain.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;

    @Override
    public Study createStudy(StudyCreateRequest request) {
        Long memberId = SecurityUtils.getMemberId();
        Study study = new Study(request, memberId);
        studyRepository.save(study);
        return study;
    }

    @Override
    public List<Study> getAllStudies() {
        Long memberId = SecurityUtils.getMemberId();
        List<Long> studyIdsByMemberId = studyMemberRepository.findStudyIdsNotJoinedByMemberId(memberId);
        List<Study> studies = new ArrayList<>();
        for (Long studyId : studyIdsByMemberId) {
            studies.add(studyRepository.findById(studyId).orElse(null));
        }
        return studies;
    }

    @Override
    public List<Study> getMyStudies() {
        Long memberId = SecurityUtils.getMemberId();
        List<Long> studyIdsByMemberId = studyMemberRepository.findStudyIdsByMemberId(memberId);
        List<Study> studies = new ArrayList<>();
        for (Long studyId : studyIdsByMemberId) {
            studies.add(studyRepository.findById(studyId).orElse(null));
        }
        return studies;
    }

    @Override
    public Optional<Study> getStudyById(Long studyId) {
        return studyRepository.findById(studyId);
    }

    @Override
    public Study updateStudy(Long studyId, StudyUpdateRequest request) {
        Study study = studyRepository.findById(studyId).orElseThrow();
        study.updateStudy(request);
        return study;
    }

    @Override
    public void deleteStudy(Long studyId) {
        Long memberId = SecurityUtils.getMemberId();
        studyMemberRepository.deleteByStudyIdAndMemberId(studyId, memberId);
    }

    @Override
    public void joinStudy(Long studyId) {
        Long memberId = SecurityUtils.getMemberId();
        StudyMember studyMember = new StudyMember(memberId, studyId);
        studyMemberRepository.save(studyMember);
    }

}
