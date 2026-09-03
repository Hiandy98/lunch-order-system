package com.lunch.ops.backend.user.entity;

public record HashedPassword(String value) {

    public HashedPassword {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("密碼雜湊值不可為空");
        }
        if (!value.startsWith("$argon2id$")) {
            throw new IllegalArgumentException("無效的 Argon2id 雜湊格式");
        }
    }
}
