package com.lunch.ops.backend.security;

import com.lunch.ops.backend.user.dto.LoginFailureResponse;
import com.lunch.ops.backend.user.dto.LoginResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tools.jackson.databind.ObjectMapper;

import java.util.Objects;

@Configuration
public class SecurityHandlerConfig {
    private final ObjectMapper objectMapper;

    public SecurityHandlerConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            User principal = (User) authentication.getPrincipal();
            Objects.requireNonNull(principal, "Principal 不可為空");

            LoginResponse responseData = new LoginResponse(
                    true,
                    "登入成功",
                    getMockJwtToken(principal)
            );

            objectMapper.writeValue(response.getWriter(), responseData);
        };
    }

    private String getMockJwtToken(User principal) {
        return "Bearer mock-jwt-token-for-" + principal.getUsername();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

            LoginFailureResponse responseData = new LoginFailureResponse(
                    false,
                    "帳號、暱稱或密碼錯誤"
            );

            objectMapper.writeValue(response.getWriter(), responseData);
        };
    }
}
