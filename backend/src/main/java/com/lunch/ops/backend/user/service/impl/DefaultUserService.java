package com.lunch.ops.backend.user.service.impl;

import com.lunch.ops.backend.common.exception.NotFoundError;
import com.lunch.ops.backend.user.repository.UserRepository;
import com.lunch.ops.backend.user.service.UserService;
import com.lunch.ops.backend.user.service.model.UserInfoResult;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserInfoResult getUserById(String id) {
        return userRepository.findById(id)
                .map(UserInfoResult::from)
                .orElseThrow(() -> new NotFoundError("找不到該使用者，ID: " + id));
    }
}
