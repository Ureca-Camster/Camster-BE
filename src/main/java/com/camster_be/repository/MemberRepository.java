package com.camster_be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.camster_be.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	// 이메일로 사용자 찾기
    Optional<Member> findByEmail(String email);
}

