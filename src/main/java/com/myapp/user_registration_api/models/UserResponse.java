package com.myapp.user_registration_api.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String created;
    private String modified;
    private String last_login;
    private String token;
    private boolean isactive;

}
