package com.camster_be.domain.auth.service;

import com.camster_be.domain.auth.dto.CustomUserDetails;
import com.camster_be.domain.auth.dto.request.LoginRequest;
import com.camster_be.domain.auth.dto.response.LoginResponse;
import com.camster_be.domain.auth.jwt.JWTUtil;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.repository.MemberRepository;
import com.camster_be.domain.util.SecurityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServiceImpl jwtTokenService;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.access}")
    private Long accessTime;
    @Value("${jwt.refresh}")
    private Long refreshTime;

    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        log.info("Login request received for email: {}", loginRequest.email());

        try {
            Member member = memberRepository.findByEmail(loginRequest.email())
                    .orElseThrow(() -> new BadCredentialsException("User not found"));

            // 사용자 인증
            Authentication authentication = authenticateUser(member.getId(), loginRequest.memberPassword());

            // SecurityContext에 인증 정보 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("User authenticated successfully: {}", authentication.getName());

            // 사용자 정보 가져오기
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long memberId = userDetails.getMemberId();

            // JWT 토큰 생성 및 쿠키 설정
            createAndSetTokens(memberId, loginRequest.email(), response);

            return LoginResponse.of(member);

        } catch (BadCredentialsException e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        } catch (Exception e) {
            log.error("Unexpected error during login: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    private Authentication authenticateUser(Long memberId, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(memberId, password);
        return authenticationManager.authenticate(authToken);
    }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            Long memberId = SecurityUtils.getMemberId(); // 사용자 ID 추출

            // 로그아웃 처리를 위해 DB 및 Redis에서 토큰 삭제
            jwtTokenService.deleteRefreshToken(memberId);
        }

        // SecurityContext를 비워서 로그아웃 처리
        SecurityContextHolder.clearContext();

        // 쿠키를 제거
        response.addCookie(removeCookie("access"));
        response.addCookie(removeCookie("refresh"));
    }

    @Override
    public void reissue(HttpServletRequest request, HttpServletResponse response) {

        // Get refresh token from cookies
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                }
            }
        }

        if (refresh == null) {
            // If no refresh token is found, throw an exception
            throw new BadCredentialsException("Invalid refresh token");
        }

        // Check if the refresh token is expired
        try {
            if (jwtUtil.isExpired(refresh)) {
                throw new ExpiredJwtException(null, null, "Refresh token expired");
            }
        } catch (JwtException e) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        // Check if the token category is refresh
        String category = jwtUtil.getCategory(refresh);
        if (!"refresh".equals(category)) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        // Extract memberId and role from the token
        Long memberId = jwtUtil.getMemberId(refresh);
        String email = jwtUtil.getEmail(refresh);
        String role = jwtUtil.getRole(refresh);

        // Create new access and refresh tokens including memberId and role
        String accessToken = jwtUtil.createToken("access", memberId, email, accessTime);
        String refreshToken = jwtUtil.createToken("refresh", memberId, email, refreshTime);

        // Save new tokens in Redis
        jwtTokenService.saveAccessToken(memberId, accessToken);
        jwtTokenService.saveRefreshToken(memberId, refreshToken);

        // Set the new tokens in the response cookies
        response.addCookie(createCookie("access", accessToken, accessTime / 1000));
        response.addCookie(createCookie("refresh", refreshToken, refreshTime / 1000));
    }

    private void createAndSetTokens(Long memberId, String email, HttpServletResponse response) {
        String accessToken = jwtUtil.createToken("access", memberId, email, accessTime);
        String refreshToken = jwtUtil.createToken("refresh", memberId, email, refreshTime);

        // 리프레시 토큰을 레디스에 저장
        jwtTokenService.saveRefreshToken(memberId, refreshToken);

        // 응답 헤더와 쿠키에 토큰 추가
        response.addCookie(createCookie("access", accessToken, accessTime / 1000));
        response.addCookie(createCookie("refresh", refreshToken, refreshTime / 1000));
    }


    private Cookie createCookie(String key, String value, Long time) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(Math.toIntExact(time));
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    private Cookie removeCookie(String key) {
        Cookie cookie = new Cookie(key, ""); // 값은 빈 문자열로 설정
        cookie.setMaxAge(0); // 쿠키를 즉시 만료시킴
        cookie.setPath("/"); // 모든 경로에서 쿠키를 사용할 수 있도록 설정
        return cookie;
    }

}
