package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.user.service.model.UserDeleteCommand;
import com.lunch.ops.backend.user.service.model.UserInfoResult;
import com.lunch.ops.backend.user.service.model.UserUpdateCommand;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    UserInfoResult getUserById(String id);

    UserInfoResult updateUserInformation(String id, UserUpdateCommand updateCommand);

    void deleteUser(String id, UserDeleteCommand deleteCommand, HttpServletResponse response);
}
