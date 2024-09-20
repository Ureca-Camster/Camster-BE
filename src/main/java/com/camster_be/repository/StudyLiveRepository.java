package com.camster_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camster_be.entity.StudyLive;

@Repository
public interface StudyLiveRepository extends JpaRepository<StudyLive, Long>{
}
