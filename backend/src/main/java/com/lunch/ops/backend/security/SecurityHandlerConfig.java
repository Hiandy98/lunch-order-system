package com.lunch.ops.backend.security;

import com.lunch.ops.backend.config.JwtSetting;
import com.lunch.ops.backend.user.dto.LoginFailureResponse;
import com.lunch.ops.backend.user.dto.LoginSuccessResponse;
import com.lunch.ops.backend.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tools.jackson.databind.ObjectMapper;

import java.util.Objects;

@Configuration
public class SecurityHandlerConfig {

    private final ObjectMapper objectMapper;
    private final JwtUtils jwtUtils;
    private final JwtSetting jwtSetting;

    public SecurityHandlerConfig(
            ObjectMapper objectMapper, JwtUtils jwtUtils, JwtSetting jwtSetting
    ) {
        this.objectMapper = objectMapper;
        this.jwtUtils = jwtUtils;
        this.jwtSetting = jwtSetting;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");

            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            Objects.requireNonNull(principal, "Principal 不可為空");

            String jwtToken = getJwtToken(authentication);

            ResponseCookie jwtCookie =  ResponseCookie.from("AUTH_TOKEN", jwtToken)
                    .httpOnly(true)
                    .secure(false)  // 我們沒有https 有了再改true
                    .path("/")  // 整個網站的API都能拿到
                    .maxAge(jwtSetting.getExpiration())
                    .sameSite("Lax")  // 防 CSRF 攻擊
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

            LoginSuccessResponse responseData = new LoginSuccessResponse(
                    true,
                    "登入成功",
                    "Bearer " + jwtToken
            );

            objectMapper.writeValue(response.getWriter(), responseData);
        };
    }

    private String getJwtToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        User userEntity = userDetails.getUserEntity();

        return jwtUtils.generateToken(userEntity);
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

    public static ResponseCookie generateLogoutCookie() {
        return ResponseCookie.from("AUTH_TOKEN", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
    }
}
