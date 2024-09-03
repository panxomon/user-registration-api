package com.myapp.user_registration_api.services;

import com.myapp.user_registration_api.domain.User;
import com.myapp.user_registration_api.models.PhoneDTO;
import com.myapp.user_registration_api.models.UserDTO;
import com.myapp.user_registration_api.models.UserResponse;
import com.myapp.user_registration_api.repository.UserRepository;
import com.myapp.user_registration_api.services.impl.UserServiceImpl;
import com.myapp.user_registration_api.utils.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Mock
    private AppConfig appConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(appConfig.getKeyValidationRegex()).thenReturn("^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$");
    }

    @Test
    void register_ValidUser_Success() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juan@rodriguez.org");
        userDTO.setPassword("Gulden1609");

        var phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("12312313");
        phoneDTO.setCountryCcde("56");
        phoneDTO.setCityCode("1");

        List<PhoneDTO> phoneList = new ArrayList<>();
        phoneList.add(phoneDTO);
        userDTO.setPhones(phoneList);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setCreated("2024-09-01T00:00:00Z");
        user.setModified("2024-09-01T00:00:00Z");
        user.setLastLogin("2024-09-01T00:00:00Z");
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserResponse response = userService.Register(userDTO);

        // Assert
        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getCreated(), response.getCreated());
        assertEquals(user.getModified(), response.getModified());
        assertEquals(user.getToken(), response.getToken());
    }

    @Test
    void register_InvalidPassword_ThrowsException() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juan@rodriguez.org");
        userDTO.setPassword("hunter2");

        var phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("12312313");
        phoneDTO.setCountryCcde("56");
        phoneDTO.setCityCode("1");

        List<PhoneDTO> phoneList = new ArrayList<>();
        phoneList.add(phoneDTO);
        userDTO.setPhones(phoneList);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.Register(userDTO);
        });
        assertEquals("La clave no cumple con el formato requerido", thrown.getMessage());
    }

    @Test
    void register_InvalidEmail_ThrowsException() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juan@rodriguez.org");
        userDTO.setPassword("hunter2");

        var phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("12312313");
        phoneDTO.setCountryCcde("56");
        phoneDTO.setCityCode("1");

        List<PhoneDTO> phoneList = new ArrayList<>();
        phoneList.add(phoneDTO);
        userDTO.setPhones(phoneList);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.Register(userDTO);
        });
        assertEquals("El formato del correo electrónico no es válido", thrown.getMessage());
    }

    @Test
    void register_ExistingEmail_ThrowsException() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juan@rodriguez.org");
        userDTO.setPassword("hunter2");
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new User()));

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.Register(userDTO);
        });
        assertEquals("El correo ya se encuentra registrado", thrown.getMessage());
    }
}
