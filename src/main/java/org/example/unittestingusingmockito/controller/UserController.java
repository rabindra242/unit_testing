package org.example.unittestingusingmockito.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.unittestingusingmockito.dto.request.UserRequestDto;
import org.example.unittestingusingmockito.service.UserService;
import org.example.unittestingusingmockito.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveUser(@RequestBody UserRequestDto userRequestDto){userService.saveUser(userRequestDto);
        return ResponseEntity.ok(userService.saveUser(userRequestDto));
    }
    @GetMapping("/getUser")
    public ResponseEntity<Response> saveUser(@RequestParam(name = "id") @Valid Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
