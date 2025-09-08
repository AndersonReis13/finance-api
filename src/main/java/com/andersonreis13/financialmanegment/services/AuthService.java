package com.andersonreis13.financialmanegment.services;

import com.andersonreis13.financialmanegment.dtos.auth.AuthLoginRequest;
import com.andersonreis13.financialmanegment.dtos.auth.AuthLoginResponse;
import com.andersonreis13.financialmanegment.dtos.auth.AuthRegisterRequest;
import com.andersonreis13.financialmanegment.dtos.auth.AuthRegisterResponse;
import com.andersonreis13.financialmanegment.entities.User;
import com.andersonreis13.financialmanegment.exceptions.BadRequestException;
import com.andersonreis13.financialmanegment.infra.jwt.JwtUtils;
import com.andersonreis13.financialmanegment.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthRegisterResponse register(AuthRegisterRequest request){
        if(userRepository.existsUserByEmail(request.email())){
            throw new BadRequestException("Usuario ja cadastrado");
        }

        String encryptedPassword = passwordEncoder.encode(request.password());

        User user = new User(request.id(),request.name(),request.email(), encryptedPassword, LocalDateTime.now(), null);

        userRepository.save(user);
        return new AuthRegisterResponse(jwtUtils.generateToken(request.email()), "Criado com sucesso");
    }

    public AuthLoginResponse login(AuthLoginRequest request){
        User user = userRepository.findUserByEmail(request.email())
                .orElseThrow(()-> new BadRequestException("Usuario não autorizado"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new BadRequestException("Usuario não autorizado");
        }

        return new AuthLoginResponse(jwtUtils.generateToken(request.email()));
    }
}
