package com.andersonreis13.financialmanegment.controllers;

import com.andersonreis13.financialmanegment.dtos.user.UserDTO;
import com.andersonreis13.financialmanegment.dtos.user.UserUpdateRequest;
import com.andersonreis13.financialmanegment.dtos.user.UserUpdateResponse;
import com.andersonreis13.financialmanegment.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest request){
        return ResponseEntity.ok().body(userService.updateUser(request));
    }
}
