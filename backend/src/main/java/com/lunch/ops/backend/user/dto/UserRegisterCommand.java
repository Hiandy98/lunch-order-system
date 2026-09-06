package com.lunch.ops.backend.user.dto;

public record UserRegisterCommand(
        String id,  // 學號
        String realName,
        String nickName,
        String classroom,
        int number,  // 座號
        String password
) {
    public UserRegisterCommand {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("學號不能為空");
        }
        if (realName == null || realName.isBlank()) {
            throw new IllegalArgumentException("真實姓名不能為空");
        }
        if (classroom == null || classroom.isBlank()) {
            throw new IllegalArgumentException("班級不能為空"); // 補上班級驗證
        }
        if (number <= 0) {
            throw new IllegalArgumentException("座號必須大於 0");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("密碼不可為空");
        }
    }
}
