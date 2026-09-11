package com.lunch.ops.backend.user.service.impl;

import com.lunch.ops.backend.common.exception.ConflictError;
import com.lunch.ops.backend.common.exception.FileParseError;
import com.lunch.ops.backend.common.exception.NotFoundError;
import com.lunch.ops.backend.common.exception.UnauthorizedError;
import com.lunch.ops.backend.security.PasswordCryptoEngine;
import com.lunch.ops.backend.user.entity.User;
import com.lunch.ops.backend.user.repository.UserRepository;
import com.lunch.ops.backend.user.service.UserService;
import com.lunch.ops.backend.user.service.model.UserDeleteCommand;
import com.lunch.ops.backend.user.service.model.UserInfoResult;
import com.lunch.ops.backend.user.service.model.UserUpdateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordCryptoEngine passwordCryptoEngine;

    public DefaultUserService(UserRepository userRepository, PasswordCryptoEngine passwordCryptoEngine) {
        this.userRepository = userRepository;
        this.passwordCryptoEngine = passwordCryptoEngine;
    }

    @Override
    public UserInfoResult getUserById(String id) {
        User user = getUserEntityById(id);
        return UserInfoResult.from(user);
    }

    private User getUserEntityById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundError(String.format("找不到該使用者，ID: %s", id)));
    }


    @Override
    @Transactional
    public UserInfoResult updateUserInformation(String id, UserUpdateCommand updateCommand) {
        User user = getUserEntityById(id);

        UserUpdateCommand mergedCommand = updateCommand.mergeWith(user);

        validateUpdateCommand(user, mergedCommand);

        user.updateProfile(
                mergedCommand.realName(),
                mergedCommand.nickName(),
                mergedCommand.classroom(),
                mergedCommand.number()
        );

        User updatedUser = userRepository.save(user);

        return UserInfoResult.from(updatedUser);
    }

    private void validateUpdateCommand(User currentUser, UserUpdateCommand command) {
        if (command.realName() == null || command.realName().isBlank()) {
            throw new FileParseError("真實姓名不可為空");
        }
        if (command.nickName() == null || command.nickName().isBlank()) {
            throw new FileParseError("暱稱不可為空");
        }
        if (command.classroom() == null || command.classroom().isBlank()) {
            throw new FileParseError("班級不可為空");
        }
        if (command.number() <= 0) {
            throw new FileParseError("座號必須大於 0");
        }

        if (!nowNameIsEqualToOldName(currentUser, command) &&
                userRepository.existsByNickName(command.nickName())) {
            throw new ConflictError("該暱稱已被使用");
        }

        if (isClassroomOrNumberChanged(currentUser, command) &&
                userRepository.existsByClassroomAndNumber(command.classroom(), command.number())) {
            throw new ConflictError(
                    String.format("%s 班的 %d 號已被註冊", command.classroom(), command.number())
            );
        }
    }

    private boolean nowNameIsEqualToOldName(User currentUser, UserUpdateCommand command) {
        return currentUser.getNickName().equals(command.nickName());
    }

    private boolean isClassroomOrNumberChanged(User currentUser, UserUpdateCommand command) {
        return !currentUser.getClassroom().equals(command.classroom())
                || currentUser.getNumber() != command.number();
    }

    @Override
    @Transactional
    public void deleteUser(String id, UserDeleteCommand deleteCommand) {
        User user = getUserEntityById(id);

        if (!user.verifyPassword(deleteCommand.rawPassword(), passwordCryptoEngine)) {
            throw new UnauthorizedError("密碼錯誤");
        }

        userRepository.delete(user);
    }
}
