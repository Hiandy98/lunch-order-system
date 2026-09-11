package com.lunch.ops.backend.user.dto;

import com.lunch.ops.backend.user.service.model.UserDeleteCommand;

public record DeleteAccountRequest(
        String rawPassword
) {
    public UserDeleteCommand toCommand(DeleteAccountRequest request) {
        return new UserDeleteCommand(rawPassword);
    }
}
