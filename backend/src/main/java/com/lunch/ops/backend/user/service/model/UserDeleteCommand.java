package com.lunch.ops.backend.user.service.model;

public record UserDeleteCommand(
        String rawPassword
) { }
