package com.lunch.ops.backend.user.service.model;

public record ChangePasswordCommand(
        String currentPassword,
        String newPassword
) { }
