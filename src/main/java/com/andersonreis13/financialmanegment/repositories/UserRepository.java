package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(@Param("email") String email);

    boolean existsUserByEmail(String email);
}
