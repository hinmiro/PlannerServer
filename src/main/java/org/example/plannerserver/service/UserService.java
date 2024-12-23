package org.example.plannerserver.service;

import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.entity.ApplicationData;
import org.example.plannerserver.entity.User;
import org.example.plannerserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> convertToDTO(user)).collect(Collectors.toList());
    }

    public UserDTO registerUser(UserDTO user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            User entityUser = userRepository.save(convertToEntity(user));
            return convertToDTO(entityUser);
        } else {
            return null;
        }
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
                .applicationData(new ApplicationData())
                .build();
    }
}
