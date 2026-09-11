package com.lunch.ops.backend.user.service.model;


import com.lunch.ops.backend.user.entity.Role;
import com.lunch.ops.backend.user.entity.User;

import java.time.LocalDateTime;

public record UserInfoResult(
        String id,
        String realName,
        String nickName,
        String classroom,
        int number,
        Role role,
        LocalDateTime updateAt
) {
    public static UserInfoResult from(User user) {
        return new UserInfoResult(
                user.getId(),
                user.getRealName(),
                user.getNickName(),
                user.getClassroom(),
                user.getNumber(),
                user.getRole(),
                user.getUpdatedAt()
        );
    }
}
