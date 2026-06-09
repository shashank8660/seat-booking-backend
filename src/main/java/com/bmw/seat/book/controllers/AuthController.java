package com.bmw.seat.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.seat.book.dto.ChangePasswordRequest;
import com.bmw.seat.book.dto.LoginRequest;
import com.bmw.seat.book.dto.LoginResponse;
import com.bmw.seat.book.entity.User;
import com.bmw.seat.book.repository.UserRepository;
import com.bmw.seat.book.services.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepo;

    //LOGIN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

    //CHANGE PASSWORD
    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequest req) {

        User user = userRepo.findByEmail(req.email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (req.newPassword == null || req.newPassword.length() < 5) {
            throw new RuntimeException("Weak password");
        }
        
        user.setPassword(req.newPassword);
        user.setFirstLogin(false);

        userRepo.save(user);

        return "SUCCESS";
    }
}
