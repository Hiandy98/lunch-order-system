package com.lunch.ops.backend.security;

import com.lunch.ops.backend.user.dto.LoginFailureResponse;
import com.lunch.ops.backend.user.dto.LoginResponse;
import com.lunch.ops.backend.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tools.jackson.databind.ObjectMapper;

import java.util.Objects;

@Configuration
public class SecurityHandlerConfig {
    private final ObjectMapper objectMapper;
    private final JwtUtils jwtUtils;

    public SecurityHandlerConfig(ObjectMapper objectMapper, JwtUtils jwtUtils) {
        this.objectMapper = objectMapper;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");

            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            Objects.requireNonNull(principal, "Principal 不可為空");

            LoginResponse responseData = new LoginResponse(
                    true,
                    "登入成功",
                    getJwtToken(authentication)
            );

            objectMapper.writeValue(response.getWriter(), responseData);
        };
    }

    private String getJwtToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        User userEntity = userDetails.getUserEntity();

        return "Bearer " + jwtUtils.generateToken(userEntity);
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
