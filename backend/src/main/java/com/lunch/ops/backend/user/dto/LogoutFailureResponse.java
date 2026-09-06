package com.lunch.ops.backend.user.dto;

import java.time.Instant;

public record LogoutFailureResponse(
        boolean success,
        String error,
        Instant timestamp
) {
    public static LogoutFailureResponse of(String error) {
        return new LogoutFailureResponse(false, error, Instant.now());
    }
}
