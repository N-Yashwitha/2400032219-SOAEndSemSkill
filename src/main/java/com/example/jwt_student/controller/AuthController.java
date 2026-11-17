package com.example.jwt_student.controller;

import com.example.jwt_student.entity.Student;
import com.example.jwt_student.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody Student student) {

        if ("student".equals(student.getUsername()) &&
            "12345".equals(student.getPassword())) {

            String token = jwtService.generateToken(
                student.getUsername()
            );

            return Map.of("token", token);
        }

        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "Invalid username or password"
        );
    }
}