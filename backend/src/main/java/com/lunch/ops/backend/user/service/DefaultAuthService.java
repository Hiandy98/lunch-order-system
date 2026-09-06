package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.security.SecurityHandlerConfig;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthService implements AuthService {

    @Override
    public void logout(HttpServletResponse response) {
        ResponseCookie logoutCookie = SecurityHandlerConfig.generateLogoutCookie();
        response.addHeader(HttpHeaders.SET_COOKIE, logoutCookie.toString());
    }
}
