package com.camster_be.domain.auth.jwt;

import com.camster_be.domain.auth.dto.CustomUserDetails;
import com.camster_be.domain.auth.service.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final JwtTokenService jwtTokenService;

    @Value("${jwt.access}")
    private Long accessTime;

    @Value("${jwt.refresh}")
    private Long refreshTime;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<Cookie> accessTokenCookie = extractCookie(request, "access");
            Optional<Cookie> refreshTokenCookie = extractCookie(request, "refresh");

            if (accessTokenCookie.isPresent()) {
                String accessToken = accessTokenCookie.get().getValue();
                processAccessToken(accessToken, refreshTokenCookie, response);
            }
        } catch (Exception e) {
            log.error("Error processing JWT", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private Optional<Cookie> extractCookie(HttpServletRequest request, String name) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> name.equals(cookie.getName()))
                        .findFirst());
    }

    private void processAccessToken(String accessToken, Optional<Cookie> refreshTokenCookie, HttpServletResponse response) throws IOException {
        try {
            if (!jwtUtil.isExpired(accessToken)) {
                authenticateUser(accessToken);
            } else {
                handleExpiredAccessToken(refreshTokenCookie, response);
            }
        } catch (ExpiredJwtException e) {
            handleExpiredAccessToken(refreshTokenCookie, response);
        }
    }

    private void handleExpiredAccessToken(Optional<Cookie> refreshTokenCookie, HttpServletResponse response) throws IOException {
        if (refreshTokenCookie.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String refreshToken = refreshTokenCookie.get().getValue();

        try {
            if (jwtUtil.isExpired(refreshToken)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            Long memberId = jwtUtil.getMemberId(refreshToken);
            String email = jwtUtil.getEmail(refreshToken);
            String storedRefreshToken = jwtTokenService.getRefreshToken(memberId);

            if (!refreshToken.equals(storedRefreshToken)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String newAccessToken = jwtUtil.createToken("access", memberId, email, accessTime);
            String newRefreshToken = jwtUtil.createToken("refresh", memberId, email, refreshTime);

            jwtTokenService.saveRefreshToken(memberId, newRefreshToken);

            response.addCookie(createCookie("access", newAccessToken, accessTime / 1000));
            response.addCookie(createCookie("refresh", newRefreshToken, refreshTime / 1000));

            authenticateUser(newAccessToken);
        } catch (ExpiredJwtException e) {
            log.debug("Refresh token expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void authenticateUser(String token) {
        String email = jwtUtil.getEmail(token);
        String role = jwtUtil.getRole(token);
        Long memberId = jwtUtil.getMemberId(token);
        CustomUserDetails customUserDetails = new CustomUserDetails(email, null, role, memberId);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private Cookie createCookie(String key, String value, Long maxAgeInSeconds) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(Math.toIntExact(maxAgeInSeconds));
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}