package com.lunch.ops.backend.user.service;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void logout(HttpServletResponse response);
}
