package com.lunch.ops.backend.user.dto;

import com.lunch.ops.backend.user.entity.User;

public record UserRegisterResult(
        String id,  // 學號
        String realName,
        String nickName,
        String classroom,
        int number  // 座號
) {
    public static UserRegisterResult from(User user) {
        return new UserRegisterResult(
                user.getId(),
                user.getRealName(),
                user.getNickName(),
                user.getClassroom(),
                user.getNumber()
        );
    }
}
