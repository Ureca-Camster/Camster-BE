package com.camster_be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.camster_be.entity.StudyMember;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long>{
	// 특정 스터디에 속한 모든 멤버 찾기
    List<StudyMember> findByStudyId(Long studyId);

    // 특정 스터디에서 특정 멤버 찾기
    Optional<StudyMember> findByMemberIdAndStudyId(Long memberId, Long studyId);
}
