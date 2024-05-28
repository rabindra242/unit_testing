package org.example.unittestingusingmockito.repository;

import org.example.unittestingusingmockito.entity.User;
import org.example.unittestingusingmockito.service.impl.UserServiceImpl;
import org.example.unittestingusingmockito.util.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTestTest {
    @Mock
    private UserRepository userRepository;
    public User user;


    @BeforeEach
    void setUp() {

    }

    @Test
    void testFindById() {
        user = new User();
        user.setUId(6);
        user.setName("John");
        user.setEmail("john@example.com");
        BDDMockito.given(userRepository.findById(6)).willReturn(Optional.of(user));
        Optional<User> user1= userRepository.findById(user.getUId());
        Assertions.assertTrue(user1.isPresent());
    }

    @AfterEach
    void tearDown() {
        reset(userRepository);
    }
}