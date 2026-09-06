package com.lunch.ops.backend.user.dto;

public record LoginSuccessResponse(
        boolean success,
        String message,
        String token
) { }
