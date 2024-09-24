package com.camster_be.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.camster_be.domain.member.entity.Record;  // 엔티티 경로에 맞게 수정

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

}
