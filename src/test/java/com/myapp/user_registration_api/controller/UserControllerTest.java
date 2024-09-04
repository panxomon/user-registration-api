package com.myapp.user_registration_api.controller;

import com.myapp.user_registration_api.models.UserDTO;
import com.myapp.user_registration_api.models.UserResponse;
import com.myapp.user_registration_api.services.UserService;
import com.myapp.user_registration_api.utils.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AppConfig appConfig;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("ValidPass123");

        UserResponse userResponse = UserResponse.builder()

                .created("2024-09-01T12:00:00")
                .modified("2024-09-01T12:00:00")
                .last_login("2024-09-01T12:00:00")
                .token("random-token")
                .isactive(true)
                .build();

        when(appConfig.getKeyValidationRegex()).thenReturn("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        when(userService.Register(any(UserDTO.class), any(String.class))).thenReturn(userResponse);

        // Act
        ResponseEntity<?> responseEntity = userController.registerUser(userDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    void testRegisterUser_InvalidEmail() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("invalid-email");
        userDTO.setPassword("ValidPass123");

        when(appConfig.getKeyValidationRegex()).thenReturn("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        when(userService.Register(any(UserDTO.class), any(String.class)))
                .thenThrow(new IllegalArgumentException("El formato del correo electrónico no es válido"));

        // Act
        ResponseEntity<?> responseEntity = userController.registerUser(userDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testRegisterUser_ExistingEmail() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("ValidPass123");

        when(appConfig.getKeyValidationRegex()).thenReturn("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        when(userService.Register(any(UserDTO.class), any(String.class)))
                .thenThrow(new IllegalArgumentException("El correo ya se encuentra registrado"));

        // Act
        ResponseEntity<?> responseEntity = userController.registerUser(userDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }


}
