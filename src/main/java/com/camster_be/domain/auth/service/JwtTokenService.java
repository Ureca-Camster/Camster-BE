package com.camster_be.domain.auth.service;

public interface JwtTokenService {

    public void saveAccessToken(Long memberId, String token);

    public void saveRefreshToken(Long memberId, String token);

    public String getAccessToken(Long memberId);

    public String getRefreshToken(Long memberId);

    public void deleteAccessToken(Long memberId);

    public void deleteRefreshToken(Long memberId);

}
