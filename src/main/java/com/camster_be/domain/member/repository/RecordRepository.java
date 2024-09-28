package com.camster_be.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.camster_be.domain.member.entity.Record;  // 엔티티 경로에 맞게 수정

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{
    List<Record> findByMemberIdAndRecordDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);
}
