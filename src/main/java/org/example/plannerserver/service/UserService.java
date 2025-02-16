package org.example.plannerserver.service;

import lombok.extern.slf4j.Slf4j;
import org.example.plannerserver.dto.UpdateUserDTO;
import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.entity.User;
import org.example.plannerserver.repository.ApplicationDataRepository;
import org.example.plannerserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationDataRepository appDataRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).toList();
    }

    public UserDTO updateUser(UpdateUserDTO updateUserDTO, UserDTO currentUser) {

        if (updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().equals(currentUser.getUsername()) && !updateUserDTO.getUsername().isEmpty()) {
            currentUser.setUsername(updateUserDTO.getUsername());
        }

        if (updateUserDTO.getPassword() != null) {
            currentUser.setPassword(encoder.encode(updateUserDTO.getPassword()));
        }

        if (updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isEmpty() && !updateUserDTO.getEmail().equals(currentUser.getEmail())) {
            currentUser.setEmail(updateUserDTO.getEmail());
        }

        User user = convertToEntity(currentUser);
        user.setUserId(currentUser.getUserId());
        user = userRepository.save(user);
        UserDTO userDTO = convertToDTO(user);
        userDTO.setJwt(currentUser.getJwt());
        return userDTO;
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
