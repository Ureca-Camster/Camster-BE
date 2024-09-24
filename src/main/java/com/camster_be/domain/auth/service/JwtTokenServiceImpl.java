package com.camster_be.domain.auth.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.access}")
    private long ACCESS_TOKEN_EXPIRATION; // 1 hour in seconds
    @Value("${jwt.refresh}")
    private long REFRESH_TOKEN_EXPIRATION; // 1 week in seconds

    public void saveAccessToken(Long memberId, String token) {
        String key = "access_token:" + memberId;
        redisTemplate.opsForValue().set(key, token, ACCESS_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
    }

    public void saveRefreshToken(Long memberId, String token) {
        String key = "refresh_token:" + memberId;
        redisTemplate.opsForValue().set(key, token, REFRESH_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
    }

    public String getAccessToken(Long memberId) {
        String key = "access_token:" + memberId;
        return redisTemplate.opsForValue().get(key);
    }

    public String getRefreshToken(Long memberId) {
        String key = "refresh_token:" + memberId;
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteAccessToken(Long memberId) {
        String key = "access_token:" + memberId;
        redisTemplate.delete(key);
    }

    public void deleteRefreshToken(Long memberId) {
        String key = "refresh_token:" + memberId;
        redisTemplate.delete(key);
    }
}