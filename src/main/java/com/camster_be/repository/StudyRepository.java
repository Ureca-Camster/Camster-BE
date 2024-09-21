package com.camster_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.camster_be.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long>{
	// memberId로 스터디를 찾는 메서드 정의
    List<Study> findByMemberId(Long memberId);
}
