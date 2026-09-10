package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.user.service.model.UserInfoResult;
import com.lunch.ops.backend.user.service.model.UserUpdateCommand;

public interface UserService {
    UserInfoResult getUserById(String id);

    UserInfoResult updateUserInformation(String id, UserUpdateCommand updateCommand);
}
