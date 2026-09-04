package com.lunch.ops.backend.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtSetting {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String getSecret() { return secret; }
    public long getExpiration() { return expiration; }
}
