package org.example.unittestingusingmockito.service;

import org.example.unittestingusingmockito.dto.request.UserRequestDto;
import org.example.unittestingusingmockito.util.Response;

public interface UserService {
    Response saveUser(UserRequestDto userRequestDto);
    Response getUserById(Integer id);
}
