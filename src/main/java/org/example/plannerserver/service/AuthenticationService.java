package org.example.plannerserver.service;

import lombok.extern.slf4j.Slf4j;
import org.example.plannerserver.dto.LoginDTO;
import org.example.plannerserver.dto.RegisterDTO;
import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.entity.ApplicationData;
import org.example.plannerserver.entity.User;
import org.example.plannerserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userRepository, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public UserDTO login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByUsername(loginDTO.getUsername());
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        if (encoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return userService.convertToDTO(user);
        } else {
            return null;
        }
    }

    public UserDTO registerUser(UserDTO user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setApplicationData(new ApplicationData());
            User entityUser = userRepository.save(userService.convertToEntity(user));
            return userService.convertToDTO(entityUser);
        } else {
            return null;
        }
    }
}
