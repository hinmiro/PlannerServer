package org.example.plannerserver.controller;

import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
}
