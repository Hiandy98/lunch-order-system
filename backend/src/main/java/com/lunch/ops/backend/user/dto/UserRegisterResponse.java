package com.lunch.ops.backend.user.dto;

public record UserRegisterResponse(
        String id,  // 學號
        String realName,
        String nickName,
        String classroom,
        int number  // 座號
) {
    public static UserRegisterResponse from(UserRegisterResult result) {
        return new UserRegisterResponse(
                result.id(),
                result.realName(),
                result.nickName(),
                result.classroom(),
                result.number()
        );
    }
}
