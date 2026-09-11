package com.lunch.ops.backend.user.dto;

import com.lunch.ops.backend.user.service.model.UserUpdateCommand;

public record UserUpdateRequest(
        String realName,
        String nickName,
        String classroom,
        Integer number
) {
    public UserUpdateCommand toCommand() {
        return new UserUpdateCommand(realName, nickName, classroom, number);
    }
}
