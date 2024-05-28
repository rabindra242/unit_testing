package org.example.unittestingusingmockito.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.unittestingusingmockito.dto.request.UserRequestDto;
import org.example.unittestingusingmockito.entity.User;
import org.example.unittestingusingmockito.repository.UserRepository;
import org.example.unittestingusingmockito.service.UserService;
import org.example.unittestingusingmockito.util.Response;
import org.example.unittestingusingmockito.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public Response saveUser(UserRequestDto userRequestDto) {
        Response response = new Response();
        if (userRequestDto==null){
            response.setMessage("User is Null");
            return response;
        }
        else {
            User users=modelMapper.map(userRequestDto, User.class);
            userRepository.save(users);
            response.setMessage("User save Succsfully");
            response.setResponse(userRequestDto);
            return response;
        }
    }

    @Override
    public Response getUserById(Integer id) {
        Response response= new Response();
        Optional<User> user=userRepository.findById(id);
        response.setMessage("User Retrived Sucessfully");
        response.setResponse(user);
        return response;
    }
}
