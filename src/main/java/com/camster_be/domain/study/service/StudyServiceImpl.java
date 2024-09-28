package com.camster_be.domain.study.service;

import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.repository.MemberRepository;
import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import com.camster_be.domain.study.dto.response.*;
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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createStudy(StudyCreateRequest request) {
        Long memberId = SecurityUtils.getMemberId();
        Study study = new Study(request, memberId);
        studyRepository.save(study);
        return study.getId();
    }

    @Override
    public List<NotMyStudyResponse> getAllStudies() {
        Long memberId = SecurityUtils.getMemberId();
        List<Long> studyIdsByMemberId = new ArrayList<>();
        if (memberId == null) {
            // not logged in
            studyIdsByMemberId = studyRepository.findAll().stream()
                    .map(Study::getId) // Study 객체에서 ID만 추출
                    .collect(Collectors.toList());
        } else {
            studyIdsByMemberId = studyMemberRepository.findStudyIdsNotJoinedByMemberId(memberId);
        }


        List<NotMyStudyResponse> studies = new ArrayList<>();
        for (Long studyId : studyIdsByMemberId) {
            studies.add(NotMyStudyResponse.of(studyRepository.findById(studyId).orElse(null)));
        }
        return studies;
    }

    @Override
    public List<MyStudyResponse> getMyStudies() {
        Long memberId = SecurityUtils.getMemberId();
        List<Long> studyIdsByMemberId = studyMemberRepository.findStudyIdsByMemberId(memberId);
        List<MyStudyResponse> studies = new ArrayList<>();
        for (Long studyId : studyIdsByMemberId) {
            studies.add( MyStudyResponse.of(studyRepository.findById(studyId).orElse(null)));
        }
        return studies;
    }

    @Override
    public StudyDetailResponse getStudyById(Long studyId) {

        // 스터디 찾기
        Study study = studyRepository.findById(studyId).orElse(null);

        // 스터디 멤버 찾기
        List<StudyMember> studyMemberLists = studyMemberRepository.findByStudyId(studyId);

        List<StudyMemberListResponse> memberList = new ArrayList<>();

        // 스터디 멤버 이름 찾기
        for (StudyMember studyMemberList : studyMemberLists) {
            Member member = memberRepository.findById(studyMemberList.getMemberId()).orElse(null);
            memberList.add( StudyMemberListResponse.of(member));
        }

        return StudyDetailResponse.of(study, memberList);
    }

    @Override
    public void updateStudy(Long studyId, StudyUpdateRequest request) {
        Study study = studyRepository.findById(studyId).orElseThrow();
        study.updateStudy(request);
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
