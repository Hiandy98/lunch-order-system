package com.lunch.ops.backend.user.dto;

public record UserRegisterCommand(
        String id,
        String realName,
        String nickName,
        String classroom,
        int number,
        String password
) {
    public UserRegisterCommand {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("學號不能為空");
        }
        if (realName == null || realName.isBlank()) {
            throw new IllegalArgumentException("真實姓名不能為空");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("密碼長度不能小於 6 位");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("座號必須大於 0");
        }
    }
}
