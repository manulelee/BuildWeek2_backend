package com.epicode.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.security.payloads.JWTAuthResponse;
import com.epicode.security.payloads.LoginDto;
import com.epicode.security.payloads.RegisterDto;
import com.epicode.security.services.AuthService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = { "/login", "/signin" })
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {

        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setUsername(loginDto.getUsername());
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = { "/register", "/signup" })
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
