package com.lunch.ops.backend.user.controller;

import com.lunch.ops.backend.user.dto.UserInfoResponse;
import com.lunch.ops.backend.user.dto.UserRegisterRequest;
import com.lunch.ops.backend.user.dto.UserRegisterResponse;
import com.lunch.ops.backend.user.dto.UserUpdateRequest;
import com.lunch.ops.backend.user.service.UserRegisterService;
import com.lunch.ops.backend.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRegisterService userRegisterService;
    private final UserService userService;

    public UserController(UserRegisterService userRegisterService, UserService userService) {
        this.userRegisterService = userRegisterService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserRegisterResponse.from(userRegisterService.execute(request.toCommand())));
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> me(@AuthenticationPrincipal String id) {
        return ResponseEntity.ok(UserInfoResponse.from(userService.getUserById(id)));
    }

    @PutMapping("/me")
    public ResponseEntity<UserInfoResponse> update(
            @AuthenticationPrincipal String id, @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity
                .ok(UserInfoResponse.from((userService.updateUserInformation(id, request.toCommand()))));
    }
}
