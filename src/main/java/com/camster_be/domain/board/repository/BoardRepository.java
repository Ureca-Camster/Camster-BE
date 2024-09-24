package com.camster_be.domain.board.repository;

import com.camster_be.domain.board.entity.Board;
import com.camster_be.domain.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByStudyId(Long studyId);

}
