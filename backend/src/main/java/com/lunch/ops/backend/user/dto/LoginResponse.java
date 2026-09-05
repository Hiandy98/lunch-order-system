package com.lunch.ops.backend.user.dto;

public record LoginResponse(
        boolean success,
        String message,
        String token
) { }
