package com.andersonreis13.financialmanegment.controllers;

import com.andersonreis13.financialmanegment.dtos.auth.AuthLoginRequest;
import com.andersonreis13.financialmanegment.dtos.auth.AuthLoginResponse;
import com.andersonreis13.financialmanegment.dtos.auth.AuthRegisterRequest;
import com.andersonreis13.financialmanegment.dtos.auth.AuthRegisterResponse;
import com.andersonreis13.financialmanegment.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponse> registerAuth(@RequestBody AuthRegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> loginAuth(@RequestBody AuthLoginRequest request){
        return ResponseEntity.ok().body(authService.login(request));
    }
}
