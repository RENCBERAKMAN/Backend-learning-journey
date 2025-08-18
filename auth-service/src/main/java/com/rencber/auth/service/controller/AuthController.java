package com.rencber.auth.service.controller;

import com.rencber.auth.service.model.User;
import com.rencber.auth.service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered: " + user.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean valid = userService.validateUser(user.getUsername(), user.getPassword());
        if (!valid) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        // Burada JWT token üretebilirsin (JwtUtil kullan)
        return ResponseEntity.ok("Login successful");
    }
}
