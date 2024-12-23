package org.example.plannerserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/api/register")
    public ResponseEntity<Object> register(@RequestBody UserDTO user) {
        UserDTO newUser = userService.registerUser(user);
        if (newUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is Taken");
        }
    }
}
