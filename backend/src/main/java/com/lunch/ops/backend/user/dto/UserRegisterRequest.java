package com.lunch.ops.backend.user.dto;

import com.lunch.ops.backend.user.service.model.UserRegisterCommand;

public record UserRegisterRequest(
        String id,  // 學號
        String realName,
        String nickName,
        String classroom,
        int number,  // 座號
        String password
) {
    public UserRegisterCommand toCommand() {
        return new UserRegisterCommand(id, realName, nickName, classroom, number, password);
    }
}
