package com.lunch.ops.backend.user.dto;

import java.time.Instant;

public record LogoutSuccessResponse(
        boolean success,
        String message,
        Instant timestamp
) {
    public static LogoutSuccessResponse of(String message) {
        return new LogoutSuccessResponse(true, message, Instant.now());
    }
}
