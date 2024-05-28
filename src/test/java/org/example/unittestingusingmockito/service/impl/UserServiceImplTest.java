package org.example.unittestingusingmockito.service.impl;

import org.example.unittestingusingmockito.dto.request.UserRequestDto;
import org.example.unittestingusingmockito.dto.response.UserResponseDto;
import org.example.unittestingusingmockito.entity.User;
import org.example.unittestingusingmockito.repository.UserRepository;
import org.example.unittestingusingmockito.util.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private UserServiceImpl userService;
    private UserRequestDto userRequestDto;

    @BeforeEach
    public void setUp(){
        userRequestDto=new UserRequestDto();
        userRequestDto.setName("John Doe");
        userRequestDto.setEmail("john.doe@example.com");
    }
    @Test
    void test_saveUser() {
        User user=new User();
        when(mapper.map(userRequestDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        Response response = userService.saveUser(userRequestDto);
        assertNotNull(response);
        assertEquals("User save Succsfully", response.getMessage());
        assertEquals(userRequestDto, response.getResponse());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById_whenUserExists() {
        User user = new User();
        user.setUId(1);
        user.setName("Raj");
        user.setEmail("raj242adk@gmail.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Response response = userService.getUserById(1);
        assertNotNull(response);
        assertEquals("User Retrived Sucessfully", response.getMessage());
        verify(userRepository, times(1)).findById(1);
    }
}