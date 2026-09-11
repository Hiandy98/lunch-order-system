package com.lunch.ops.backend.security;

import com.lunch.ops.backend.user.entity.HashedPassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PasswordCryptoEngine implements PasswordEncoder {
    private final Argon2PasswordEncoder argon2;
    private final String pepper;

    //TODO pepper 暫時先這樣注入 之後用 setting 之類的做法
    public PasswordCryptoEngine(@Value("${security.password.pepper}") String pepper) {
        this.argon2 = new Argon2PasswordEncoder(16, 32, 1, 65536, 3);
        this.pepper = pepper;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        return argon2.encode(rawPassword + pepper);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return argon2.matches(rawPassword + pepper, encodedPassword);
    }

    public HashedPassword hash(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("原始密碼不可為空");
        }
        return new HashedPassword(encode(rawPassword));
    }

    public boolean verify(String rawPassword, HashedPassword hashedPassword) {
        if (rawPassword == null || hashedPassword == null) {
            return false;
        }
        return matches(rawPassword, hashedPassword.value());
    }
}
