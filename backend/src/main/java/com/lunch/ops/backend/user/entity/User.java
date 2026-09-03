package com.lunch.ops.backend.user.entity;

import com.lunch.ops.backend.security.PasswordCryptoEngine;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 給 JPA/Hibernate 用，外部無法直接 new
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(length = 20)
    private String id;

    @Column(nullable = false, length = 100)
    private String realName;

    @Column(nullable = false, length = 100)
    private String nickName;

    @Column(nullable = false, length = 50)
    private String classroom;

    @Column(nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.USER;

    @Column(nullable = false, length = 255)
    private String password;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static User register(
            String id,
            String realName,
            String nickName,
            String classroom,
            int number,
            HashedPassword hashedPassword
    ) {
        return create(id, realName, nickName, classroom, number, Role.USER, hashedPassword);
    }

    public static User create(
            String id,
            String realName,
            String nickName,
            String classroom,
            int number,
            Role role,
            HashedPassword hashedPassword
    ) {
        User user = new User();
        user.id = Objects.requireNonNull(id, "使用者 ID 不可為空");
        user.updateProfile(realName, nickName, classroom, number);
        user.changeRole(role);
        user.changePassword(hashedPassword);
        return user;
    }

    public void updateProfile(String realName, String nickName, String classroom, int number) {
        if (realName == null || realName.isBlank()) {
            throw new IllegalArgumentException("真實姓名不可為空");
        }
        if (classroom == null || classroom.isBlank()) {
            throw new IllegalArgumentException("班級不可為空");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("座號必須大於 0");
        }
        this.realName = realName;
        this.nickName = (nickName != null && !nickName.isBlank()) ? nickName : realName;
        this.classroom = classroom;
        this.number = number;
    }

    public void changePassword(HashedPassword newHashedPassword) {
        this.password = Objects.requireNonNull(newHashedPassword, "密碼不可為空").value();
    }

    public void changeRole(Role newRole) {
        this.role = Objects.requireNonNull(newRole, "身分不可為空");
    }

    public boolean verifyPassword(String rawPassword, PasswordCryptoEngine cryptoEngine) {
        return cryptoEngine.matches(rawPassword, this.password);
    }
}