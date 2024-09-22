package com.camster_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camster_be.entity.StudyMember;
import com.camster_be.repository.StudyMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudyMemberService {

    @Autowired
    private StudyMemberRepository studyMemberRepository;

    // 스터디에 새로운 멤버 추가
    public StudyMember createStudyMember(StudyMember studyMember) {
        return studyMemberRepository.save(studyMember);
    }

    // 특정 스터디의 멤버 목록 가져오기
    public List<StudyMember> getStudyMembersByStudyId(Long studyId) {
        return studyMemberRepository.findByStudyId(studyId);
    }

    // 특정 스터디에서 특정 멤버 조회
    public Optional<StudyMember> getStudyMemberByIdAndStudyId(Long memberId, Long studyId) {
        return studyMemberRepository.findByMemberIdAndStudyId(memberId, studyId);
    }

    // 스터디에서 멤버 삭제
    public void deleteStudyMember(Long memberId, Long studyId) {
        Optional<StudyMember> studyMember = studyMemberRepository.findByMemberIdAndStudyId(memberId, studyId);
        studyMember.ifPresent(studyMemberRepository::delete);
    }
}
