package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.user.dto.UserRegisterCommand;
import com.lunch.ops.backend.user.dto.UserRegisterResult;
import com.lunch.ops.backend.user.entity.HashedPassword;
import com.lunch.ops.backend.user.entity.User;
import com.lunch.ops.backend.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserRegisterService implements UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserRegisterResult execute(UserRegisterCommand command) {
        if (userRepository.existsById(command.id())) {
            throw new IllegalArgumentException("該學號已被註冊");
        }
        if (userRepository.existsByNickName(command.nickName())) {
            throw new IllegalArgumentException("該暱稱已被使用");
        }
        if (userRepository.existsByClassroomAndNumber(command.classroom(), command.number())) {
            throw new IllegalArgumentException(
                    String.format("%s 班的 %d 號已被註冊", command.classroom(), command.number())
            );
        }

        User user = User.register(
                command.id(),
                command.realName(),
                command.nickName(),
                command.classroom(),
                command.number(),
                generateHashedPassword(command.password())
        );

        User savedUser = userRepository.save(user);

        return UserRegisterResult.from(savedUser);
    }

    private HashedPassword generateHashedPassword(String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        return new HashedPassword(encodedPassword);
    }
}
