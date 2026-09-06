package com.lunch.ops.backend.user.controller;

import com.lunch.ops.backend.user.dto.UserRegisterRequest;
import com.lunch.ops.backend.user.dto.UserRegisterResponse;
import com.lunch.ops.backend.user.service.UserRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRegisterService userRegisterService;

    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserRegisterResponse.from(userRegisterService.execute(request.toCommand())));
    }
}
