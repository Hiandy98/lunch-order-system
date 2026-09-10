package com.lunch.ops.backend.user.dto;


import com.lunch.ops.backend.user.entity.Role;
import com.lunch.ops.backend.user.service.model.UserInfoResult;

import java.time.LocalDateTime;

public record UserInfoResponse(
        String id,
        String realName,
        String nickName,
        String classroom,
        int number,
        Role role,
        LocalDateTime updatedAt
) {
    public static UserInfoResponse from(UserInfoResult result) {
        return new UserInfoResponse(
                result.id(),
                result.realName(),
                result.nickName(),
                result.classroom(),
                result.number(),
                result.role(),
                result.updateAt()
        );
    }
}
