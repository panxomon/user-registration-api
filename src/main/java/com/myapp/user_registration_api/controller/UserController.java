package com.myapp.user_registration_api.controller;

import com.myapp.user_registration_api.models.UserResponse;
import com.myapp.user_registration_api.models.UserDTO;
import com.myapp.user_registration_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService uService) {
        this.userService = uService;
    }
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {            
            UserResponse response = userService.Register(userDTO);
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {            
            
            return new ResponseEntity<>(Map.of("mensaje", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
