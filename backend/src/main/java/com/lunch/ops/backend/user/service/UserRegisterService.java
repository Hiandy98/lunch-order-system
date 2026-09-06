package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.user.dto.UserRegisterCommand;
import com.lunch.ops.backend.user.dto.UserRegisterResult;

public interface UserRegisterService {
    UserRegisterResult execute(UserRegisterCommand command);
}
