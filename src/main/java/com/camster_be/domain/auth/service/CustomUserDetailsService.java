package com.camster_be.domain.auth.service;

import com.camster_be.domain.auth.dto.CustomUserDetails;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Long memberId;
		try {
			memberId = Long.parseLong(username);
		} catch (NumberFormatException e) {
			throw new UsernameNotFoundException("Invalid member ID format");
		}
		Member userData = memberRepository.findById(memberId)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with memberId: " + memberId));

		log.info("userData = {}", userData);

		return new CustomUserDetails(
				userData.getEmail(),
				userData.getMemberPassword(), // 비밀번호 포함
				userData.getRole(),
				userData.getId()
		);
	}
}