package com.machinery.grievance_system.service;

import com.machinery.grievance_system.dao.UserDao;
import com.machinery.grievance_system.model.User;
import com.machinery.grievance_system.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    public AuthService(UserDao userDao, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {

        User user = userDao.findByEmail(email);

        // User not found
        if (user == null) {
            return "INVALID USER";
        }

        // Password check (PLAIN for now — your DB passwords are NOT encrypted)
        if (!password.equals(user.getPassword())) {
            return "INVALID PASSWORD";
        }

        // Generate JWT Token
        return jwtUtil.generateToken(user.getEmail());
    }
}
