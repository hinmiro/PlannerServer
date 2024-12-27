package org.example.plannerserver.service;

import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.entity.User;
import org.example.plannerserver.repository.ApplicationDataRepository;
import org.example.plannerserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final UserRepository userRepository;
    private final ApplicationDataRepository appDataRepository;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationDataRepository appDataRepository) {
        this.userRepository = userRepository;
        this.appDataRepository = appDataRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> convertToDTO(user)).collect(Collectors.toList());
    }

    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .applicationData(user.getApplicationData())
                .build();
    }

    public User convertToEntity(UserDTO user) {
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .applicationData(user.getApplicationData())
                .build();
    }
}
