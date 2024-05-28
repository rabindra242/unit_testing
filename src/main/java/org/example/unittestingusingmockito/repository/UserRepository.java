package org.example.unittestingusingmockito.repository;

import org.example.unittestingusingmockito.entity.User;
import org.example.unittestingusingmockito.util.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id);
}

