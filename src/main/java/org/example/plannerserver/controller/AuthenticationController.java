package org.example.plannerserver.controller;

import org.example.plannerserver.dto.LoginDTO;
import org.example.plannerserver.dto.RegisterDTO;
import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@RequestBody LoginDTO loginDTO) {

        if (loginDTO.getUsername() == null || loginDTO.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing username or password");
        }
        UserDTO loggedUser = authenticationService.login(loginDTO);
        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
        return ResponseEntity.status(200).body(loggedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDTO user) {
        UserDTO newUser = authenticationService.registerUser(user);
        if (newUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is Taken");
        }
    }
}
