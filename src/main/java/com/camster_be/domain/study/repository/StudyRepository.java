package com.camster_be.domain.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.camster_be.domain.study.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long>{

}
