package com.example.BackendTask.security;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@AllArgsConstructor
@Log4j2
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsService customUserDetailsService;


    public TokenAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("validate token");
            String jwt = getJwtToken(httpServletRequest, true);
            if (tokenProvider.validateToken(jwt)) {
                log.info("token is valid ! ");
                String username = tokenProvider.getUsernameFromToken(jwt);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("error in validating token", ex);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String accessToken = bearerToken.substring(7);
            if (accessToken == null) return null;

            //  return SecurityCipher.decrypt(accessToken);
            return accessToken;
        }
        return null;
    }

    private String getJwtFromCookie(HttpServletRequest request) {
        log.info("loading cookies from request");
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            log.info("no cookies found in the request");
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("accessToken".equals(cookie.getName())) {
                log.info("accessToken cookie has been found");
                String accessToken = cookie.getValue();
                if (accessToken == null) return null;

                //  return SecurityCipher.decrypt(accessToken);
                return accessToken;
            }
        }
        return null;
    }

    private String getJwtToken(HttpServletRequest request, boolean fromCookie) {
        if (fromCookie) return getJwtFromCookie(request);

        return getJwtFromRequest(request);
    }
}

