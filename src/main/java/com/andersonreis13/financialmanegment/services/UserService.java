package com.andersonreis13.financialmanegment.services;

import com.andersonreis13.financialmanegment.dtos.user.UserDTO;
import com.andersonreis13.financialmanegment.dtos.user.UserUpdateRequest;
import com.andersonreis13.financialmanegment.dtos.user.UserUpdateResponse;
import com.andersonreis13.financialmanegment.entities.User;
import com.andersonreis13.financialmanegment.exceptions.BadRequestException;
import com.andersonreis13.financialmanegment.exceptions.NotFoundException;
import com.andersonreis13.financialmanegment.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Usuario não encontrado"));
        return new UserDTO(user.getId(),user.getName(), user.getEmail());
    }

    public UserUpdateResponse updateUser(UserUpdateRequest request){
        User user = userRepository.findById(request.id())
                .orElseThrow(()-> new NotFoundException("Usuario não encontrado"));

        if(!Objects.equals(request.email(), user.getEmail())){
            isEmailAlreadyinUse(request.email());
        }

        user.setEmail(request.email());
        user.setName(request.name());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new UserUpdateResponse("Usuario atualizado com sucesso", user.getEmail(), user.getName());
    }

    public void isEmailAlreadyinUse(String email){
        if(userRepository.existsUserByEmail(email)){
            throw new BadRequestException("Já existe um usuario com esse email");
        }
    }
}
