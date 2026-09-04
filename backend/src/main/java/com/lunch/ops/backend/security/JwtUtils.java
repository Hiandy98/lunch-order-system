package com.lunch.ops.backend.security;

import com.lunch.ops.backend.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private final SecretKey jwtSignerKey;
    private final JwtSetting jwtSetting;

    public JwtUtils(JwtSetting jwtSetting) {
        this.jwtSetting = jwtSetting;
        this.jwtSignerKey = Keys.hmacShaKeyFor(
                jwtSetting.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(User user) {
        Date now = new Date();

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .issuedAt(now)
                .expiration(calculateExpiryTime(now))
                .claim("name", user.getNickName())
                .claim("role", user.getRole())
                .claim("classroom", user.getClassroom())
                .claim("number", user.getNumber())
                .signWith(jwtSignerKey)
                .compact();
    }

    private Date calculateExpiryTime(Date now) {
        return new Date(now.getTime() + jwtSetting.getExpiration());
    }
}
