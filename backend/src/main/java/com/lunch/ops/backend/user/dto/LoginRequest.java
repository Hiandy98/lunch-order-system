package com.lunch.ops.backend.user.dto;

public record LoginRequest(
        String loginInput,
        String password
) {
    @Override
    public String toString() {
        return "LoginRequest[loginInput=" + loginInput + ", password=********]";
    }
}
