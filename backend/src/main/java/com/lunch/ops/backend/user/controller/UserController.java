package com.lunch.ops.backend.user.controller;

import com.lunch.ops.backend.user.dto.*;
import com.lunch.ops.backend.user.service.UserRegisterService;
import com.lunch.ops.backend.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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

    @PatchMapping("/me")
    public ResponseEntity<UserInfoResponse> update(
            @AuthenticationPrincipal String id, @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity
                .ok(UserInfoResponse.from((userService.updateUserInformation(id, request.toCommand()))));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal String id, @RequestBody DeleteAccountRequest request, HttpServletResponse response
    ) {
        userService.deleteUser(id, request.toCommand(), response);
        return ResponseEntity.noContent().build();
    }

}
