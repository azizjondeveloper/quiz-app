package com.example.quizapp.repository;

import com.example.quizapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User,UUID> {




    boolean existsByUsername(String username);


    Optional<User> findByUsername(String username);

    Optional<User> findByEmailCode(UUID code);
}

