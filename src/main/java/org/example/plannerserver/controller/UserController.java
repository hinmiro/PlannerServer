package org.example.plannerserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.plannerserver.dto.UpdateUserDTO;
import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.service.UserContextService;
import org.example.plannerserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserContextService userContextService;

    @Autowired
    public UserController(UserService userService, UserContextService userContextService) {
        this.userService = userService;
        this.userContextService = userContextService;
    }


    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PutMapping("/users/update")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {

        Optional<UserDTO> optionalUserDTO = userContextService.getCurrentUser();
        if (optionalUserDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        UserDTO userDTO = userService.updateUser(updateUserDTO, optionalUserDTO.get());
        userDTO.setJwt(optionalUserDTO.get().getJwt());

        return ResponseEntity.status(200).body(userDTO);
    }
}
