package com.example.jwt_student.controller;

import com.example.jwt_student.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AccountController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/account/details")
    public String getAccount(
            @RequestHeader(value = "Authorization",
                           required = false) String header) {

        try {

            if (header == null ||
                !header.startsWith("Bearer ")) {

                throw new Exception("Invalid token");
            }

            String token = header.substring(7);

            String username =
                jwtService.validateToken(token);

            return "Welcome " + username +
                   ". Account details accessed successfully";

        } catch (Exception e) {

            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Invalid or expired token"
            );
        }
    }
}
