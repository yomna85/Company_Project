package com.example.BackendTask.security;

import com.example.BackendTask.entity.User;
import com.example.BackendTask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Log4j2
public class AuthHandler {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    private final CookieUtil cookieUtil;
    private final UserService userService;

    @Value("${app.jwt.token.expiration-in-ms}")
    private Long tokenExpirationMillis;

    @Value("${app.jwt.refresh.expiration-in-ms}")
    private Long refreshTokenExpirationMillis;

    public ResponseEntity<?> login(LoginRequestDto loginRequest) {
        log.info("start login for user {}", loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("create cookies for user {}", loginRequest.getUsername());
        HttpHeaders responseHeaders = new HttpHeaders();
        String accessToken = tokenProvider.generateAccessToken(loginRequest.getUsername());
        String refreshToken = tokenProvider.generateRefreshToken(loginRequest.getUsername());
        addAccessTokenCookie(responseHeaders, accessToken);
        addRefreshTokenCookie(responseHeaders, refreshToken);
        return ResponseEntity.ok().body(new LoginResponseDto(accessToken, refreshToken));
    }

    public ResponseEntity<?> logout() {
        log.info("logout current user");
        HttpHeaders responseHeaders = new HttpHeaders();
        deleteAccessTokenCookie(responseHeaders);
        deleteRefreshTokenCookie(responseHeaders);
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    public ResponseEntity<?> refresh(String refreshToken) {
        Boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);
        if (!refreshTokenValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUsername = tokenProvider.getUsernameFromToken(refreshToken);

        String newAccessToken = tokenProvider.generateAccessToken(currentUsername);
        HttpHeaders responseHeaders = new HttpHeaders();
        addAccessTokenCookie(responseHeaders, newAccessToken);

        return ResponseEntity.ok().headers(responseHeaders).build();
    }


    public ResponseEntity<?> getUserInfo(UserDetails userDetails) {
        UserDto userDto = UserDto.builder()
                .username(userDetails.getUsername())
                .authorities((Set<GrantedAuthority>) userDetails.getAuthorities())
                .build();
        // TODO HERE WE Need to Add  temporary Password Impl
        return ResponseEntity.ok(userDto);


    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, String token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token, tokenExpirationMillis).toString());
    }

    private void deleteAccessTokenCookie(HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteAccessTokenCookie().toString());
    }

    private void addRefreshTokenCookie(HttpHeaders httpHeaders, String token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(token, refreshTokenExpirationMillis).toString());
    }

    private void deleteRefreshTokenCookie(HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteRefreshTokenCookie().toString());
    }

}
