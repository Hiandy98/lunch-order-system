package com.lunch.ops.backend.user.dto;

public record LoginFailureResponse(
        boolean success,
        String message
) {}