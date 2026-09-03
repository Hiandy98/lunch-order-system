package com.lunch.ops.backend.security;

import com.lunch.ops.backend.user.entity.HashedPassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PasswordCryptoEngine {
    private final PasswordEncoder passwordEncoder;
    private final String pepper;

    //TODO pepper 暫時先這樣注入 之後用 setting 之類的做法
    public PasswordCryptoEngine(
            PasswordEncoder passwordEncoder,
            @Value("${security.value.pepper}") String pepper
    ) {
        this.passwordEncoder = passwordEncoder;
        this.pepper = pepper;
    }

    public HashedPassword encode(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("原始密碼不可為空");
        }
        String hashed = passwordEncoder.encode(rawPassword + pepper);
        return new HashedPassword(hashed);
    }

    public boolean matches(String rawPassword, String hashedPasswordValue) {
        if (rawPassword == null || hashedPasswordValue == null) {
            return false;
        }
        return passwordEncoder.matches(rawPassword + pepper, hashedPasswordValue);
    }
}
