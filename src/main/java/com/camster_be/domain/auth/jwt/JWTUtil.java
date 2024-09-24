package com.camster_be.domain.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JWTUtil {

    private final SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getRole(String token) {
        return getClaim(token, "role", String.class);
    }

    public String getEmail(String token) {
        return getClaim(token, "email", String.class);
    }


    public Long getMemberId(String token) {
        return getClaim(token, "memberId", Long.class);
    }

    public String getCategory(String token) {
        return getClaim(token, "category", String.class);
    }

    public Boolean isExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            log.error("JWT validation failed: {}", e.getMessage());
            return true;
        }
    }

    public String createToken(String category, Long memberId, String email, Long expiredMs) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiredMs);

        return Jwts.builder()
                .claim("category", category)
                .claim("memberId", memberId)
                .claim("email", email)
                .claim("role", "ROLE_USER")
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    private <T> T getClaim(String token, String claimName, Class<T> requiredType) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get(claimName, requiredType);
        } catch (JwtException e) {
            log.error("Failed to get claim '{}' from JWT: {}", claimName, e.getMessage());
            return null;
        }
    }
}

