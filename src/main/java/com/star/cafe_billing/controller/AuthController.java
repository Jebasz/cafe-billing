package com.star.cafe_billing.controller;

import com.star.cafe_billing.dto.AuthResponse;
import com.star.cafe_billing.entity.User;
import com.star.cafe_billing.repository.UserRepository;
import com.star.cafe_billing.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User request){

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if(!user.getPassword()
                .equals(request.getPassword())){

            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(
                token,
                user.getRole().name(),
                user.getUsername()
        );
    }

}