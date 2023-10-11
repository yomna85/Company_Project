package com.example.BackendTask.security;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    private static final String ACCESS_TOKEN = "accessToken";
    private static final String REFRESH_TOKEN = "refreshToken";

    public HttpCookie createAccessTokenCookie(String token, Long duration) {
        //TODO:add encryption/decryption
        //TODO:add secure= true
        return ResponseCookie.from(ACCESS_TOKEN, token)
                .maxAge(duration / 1000)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie createRefreshTokenCookie(String token, Long duration) {
        //TODO:add encryption/decryption
        //TODO:add secure= true
        return ResponseCookie.from(REFRESH_TOKEN, token)
                .maxAge(duration / 1000)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie deleteAccessTokenCookie() {
        return ResponseCookie.from(ACCESS_TOKEN, null)
                .maxAge(0)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie deleteRefreshTokenCookie() {
        return ResponseCookie.from(REFRESH_TOKEN, null)
                .maxAge(0)
                .httpOnly(true)
                .path("/")
                .build();
    }
}

