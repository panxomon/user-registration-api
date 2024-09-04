package com.myapp.user_registration_api.controller;

import com.myapp.user_registration_api.models.UserResponse;
import com.myapp.user_registration_api.models.UserDTO;
import com.myapp.user_registration_api.services.UserService;
import com.myapp.user_registration_api.utils.AppConfig;
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
    private final AppConfig appConfig;

    public UserController(UserService uService, AppConfig appConfig) {
        this.userService = uService;
        this.appConfig = appConfig;
    }
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            String keyValidationRegex = appConfig.getKeyValidationRegex();
            UserResponse response = userService.Register(userDTO, keyValidationRegex);
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {            
            
            return new ResponseEntity<>(Map.of("mensaje", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
