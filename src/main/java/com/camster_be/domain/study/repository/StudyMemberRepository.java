package com.camster_be.domain.study.repository;

import com.camster_be.domain.study.entity.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

    // 사용자가 포함된 스터디의 ID 목록 조회
    @Query("SELECT distinct sm.studyId FROM StudyMember sm WHERE sm.memberId = :memberId")
    List<Long> findStudyIdsByMemberId(@Param("memberId") Long memberId);

    // 사용자가 포함되지 않은 스터디의 ID 목록 조회
    @Query("SELECT distinct s.id FROM Study s WHERE s.id NOT IN (SELECT sm.studyId FROM StudyMember sm WHERE sm.memberId = :memberId)")
    List<Long> findStudyIdsNotJoinedByMemberId(@Param("memberId") Long memberId);

    void deleteByStudyIdAndMemberId(Long studyId, Long memberId);
}