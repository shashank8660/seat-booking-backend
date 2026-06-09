package com.bmw.seat.book.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmw.seat.book.dto.LoginRequest;
import com.bmw.seat.book.dto.LoginResponse;
import com.bmw.seat.book.entity.User;
import com.bmw.seat.book.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    private final String DEFAULT_PASSWORD = "Welcome@123";

    public LoginResponse login(LoginRequest req) {

        String email = req.email;
        String password = req.password;
        
        //restrict the domain to bmw folks
        if (!email.endsWith("@bmwtechworks.in")) {
            return new LoginResponse(false, false);
        }

        Optional<User> userOpt = userRepo.findByEmail(email);
        
        //existing user
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (user.getPassword().equals(DEFAULT_PASSWORD) && !user.isFirstLogin()) {
                return new LoginResponse(false, false);
            }
  
            if (user.getPassword().equals(password)) {
                return new LoginResponse(true, user.isFirstLogin());
            }

            return new LoginResponse(false, false);
        }

        //New user - allow default password only
        if (password.equals(DEFAULT_PASSWORD)) {

            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setFirstLogin(true);

            userRepo.save(newUser);

            return new LoginResponse(true, true);
        }

        return new LoginResponse(false, false);
    }
}

