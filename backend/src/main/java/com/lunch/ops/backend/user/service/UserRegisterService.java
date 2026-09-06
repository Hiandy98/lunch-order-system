package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.user.service.model.UserRegisterCommand;
import com.lunch.ops.backend.user.service.model.UserRegisterResult;

public interface UserRegisterService {
    UserRegisterResult execute(UserRegisterCommand command);
}
