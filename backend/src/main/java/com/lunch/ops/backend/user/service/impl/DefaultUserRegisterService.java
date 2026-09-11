package com.lunch.ops.backend.user.service.impl;

import com.lunch.ops.backend.common.exception.ConflictError;
import com.lunch.ops.backend.security.PasswordCryptoEngine;
import com.lunch.ops.backend.user.service.UserRegisterService;
import com.lunch.ops.backend.user.service.model.UserRegisterCommand;
import com.lunch.ops.backend.user.service.model.UserRegisterResult;
import com.lunch.ops.backend.user.entity.HashedPassword;
import com.lunch.ops.backend.user.entity.User;
import com.lunch.ops.backend.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserRegisterService implements UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordCryptoEngine passwordCryptoEngine;

    public DefaultUserRegisterService(UserRepository userRepository, PasswordCryptoEngine passwordCryptoEngine) {
        this.userRepository = userRepository;
        this.passwordCryptoEngine = passwordCryptoEngine;
    }

    @Override
    @Transactional
    public UserRegisterResult execute(UserRegisterCommand command) {
        validateRegisterCommand(command);

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
        return passwordCryptoEngine.hash(rawPassword);
    }

    private void validateRegisterCommand(UserRegisterCommand command) {
        if (userRepository.existsById(command.id())) {
            throw new ConflictError("該學號已被註冊");
        }
        if (userRepository.existsByNickName(command.nickName())) {
            throw new ConflictError("該暱稱已被使用");
        }
        if (userRepository.existsByClassroomAndNumber(command.classroom(), command.number())) {
            throw new ConflictError(
                    String.format("%s 班的 %d 號已被註冊", command.classroom(), command.number())
            );
        }
    }
}
