package com.lunch.ops.backend.user.dto;

import com.lunch.ops.backend.user.service.model.ChangePasswordCommand;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword
) {
    public ChangePasswordCommand toCommand() {
        return new ChangePasswordCommand(currentPassword, newPassword);
    }
}
