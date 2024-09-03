package com.myapp.user_registration_api.services.impl;

import com.myapp.user_registration_api.domain.User;
import com.myapp.user_registration_api.models.UserResponse;
import com.myapp.user_registration_api.models.UserDTO;
import com.myapp.user_registration_api.repository.UserRepository;
import com.myapp.user_registration_api.services.UserService;
import com.myapp.user_registration_api.utils.AppConfig;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    private final UserRepository repository;
    private final AppConfig appConfig;
    private final Pattern keyPattern;

    public UserServiceImpl(UserRepository repository, AppConfig appConfig) {
        this.repository = repository;
        this.appConfig = appConfig;

        String regex = appConfig.getKeyValidationRegex();
        if (regex == null || regex.isEmpty()) {
            throw new IllegalArgumentException("Invalid regex pattern");
        }
        this.keyPattern = Pattern.compile(regex);
    }

    public UserResponse Register(UserDTO userDTO) {

        var matcher = keyPattern.matcher(userDTO.getPassword());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("La clave no cumple con el formato requerido");
        }

        if (!emailPattern.matcher(userDTO.getEmail()).matches()) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }
        Optional<User> existingUser = repository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado");
        }

        
        var user = repository.save(User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .created(String.valueOf(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())))
                .modified(String.valueOf(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())))
                .lastLogin(String.valueOf(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())))
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .build());

        
        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .last_login(user.getCreated())
                .token(user.getToken())
                .isactive(user.isActive())
                .build();

        return response;
    }
}
