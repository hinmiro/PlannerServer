package org.example.plannerserver.service;

import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.entity.User;
import org.example.plannerserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserContextService {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserContextService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Optional<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            User currentUser = userRepository.findByUsername(authentication.getName()).orElseThrow();
            return Optional.of(userService.convertToDTO(currentUser));
        }
        return Optional.empty();
    }
}
