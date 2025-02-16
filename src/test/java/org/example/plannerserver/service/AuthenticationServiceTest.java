package org.example.plannerserver.service;

import org.example.plannerserver.dto.LoginDTO;
import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.entity.User;
import org.example.plannerserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    void testLoginSuccess() {
        LoginDTO loginDTO = new LoginDTO("user", "password");
        User user = new User();
        user.setUsername("user");
        user.setPassword(encoder.encode("password"));

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(userService.convertToDTO(user)).thenReturn(new UserDTO());
        when(jwtService.generateToken(any(UserDTO.class))).thenReturn("jwt-token");

        UserDTO result = authenticationService.login(loginDTO);

        assertNotNull(result);
        assertEquals("jwt-token", result.getJwt());
    }

    @Test
    void testLoginFailure() {

        LoginDTO loginDTO = new LoginDTO("user", "wrongpassword");
        User user = new User();
        user.setUsername("user");
        user.setPassword(encoder.encode("password"));

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));

        UserDTO result = authenticationService.login(loginDTO);

        assertNull(result);
    }

    @Test
    void testRegisterUserSuccess() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newuser");
        userDTO.setPassword("password");

        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(userService.convertToEntity(any(UserDTO.class))).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userService.convertToDTO(any(User.class))).thenReturn(userDTO);
        when(jwtService.generateToken(any(UserDTO.class))).thenReturn("jwt-token");

        UserDTO result = authenticationService.registerUser(userDTO);

        assertNotNull(result);
        assertEquals("jwt-token", result.getJwt());
    }

    @Test
    void testRegisterUserFailure() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("existinguser");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(new User()));

        UserDTO result = authenticationService.registerUser(userDTO);

        assertNull(result);
    }
}