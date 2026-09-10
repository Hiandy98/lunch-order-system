package com.lunch.ops.backend.user.service.model;

public record UserUpdateCommand(
        String realName,
        String nickName,
        String classroom,
        int number
) { }
