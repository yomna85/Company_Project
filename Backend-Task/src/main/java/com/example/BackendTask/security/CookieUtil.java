package com.example.BackendTask.security;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public HttpCookie createAccessTokenCookie(String token, Long duration) {
        //TODO:add encryption/decryption
        //TODO:add secure= true
        return ResponseCookie.from("accessToken", token)
                .maxAge(duration / 1000)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie createRefreshTokenCookie(String token, Long duration) {
        //TODO:add encryption/decryption
        //TODO:add secure= true
        return ResponseCookie.from("refreshToken", token)
                .maxAge(duration / 1000)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie deleteAccessTokenCookie() {
        return ResponseCookie.from("accessToken", null)
                .maxAge(0)
                .httpOnly(true)
                .path("/")
                .build();
    }
}


