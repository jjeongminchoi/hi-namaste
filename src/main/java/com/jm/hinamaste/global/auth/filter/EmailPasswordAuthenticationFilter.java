package com.jm.hinamaste.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.hinamaste.global.auth.dto.LoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class EmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/auth/login", "POST");
    private final ObjectMapper objectMapper;

    public EmailPasswordAuthenticationFilter(ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        LoginRequest loginRequest = getLoginRequest(request);
        UsernamePasswordAuthenticationToken authRequest = getAuthenticationToken(loginRequest);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private LoginRequest getLoginRequest(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getInputStream(), LoginRequest.class);
    }

    private static UsernamePasswordAuthenticationToken getAuthenticationToken(LoginRequest loginRequest) {
        return UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
    }

    private void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
