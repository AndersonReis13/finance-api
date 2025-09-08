package com.andersonreis13.financialmanegment.infra;

import com.andersonreis13.financialmanegment.entities.User;
import com.andersonreis13.financialmanegment.exceptions.NotFoundException;
import com.andersonreis13.financialmanegment.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CostumerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CostumerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()-> new NotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
