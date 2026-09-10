package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.user.service.model.UserInfoResult;

public interface UserService {
    UserInfoResult getUserById(String id);
}
