package com.camster_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.camster_be.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	// 스터디 ID로 게시물 목록 조회
    List<Board> findByStudyId(Long studyId);
    
}
