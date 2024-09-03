package com.myapp.user_registration_api.services;

import com.myapp.user_registration_api.models.UserResponse;
import com.myapp.user_registration_api.models.UserDTO;

public interface UserService {
    UserResponse Register(UserDTO userDTO);
}
