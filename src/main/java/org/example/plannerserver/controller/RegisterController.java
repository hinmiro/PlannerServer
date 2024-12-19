package org.example.plannerserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegisterController {

    @PostMapping("/api/register")
    public List<String> register() {
        return List.of("hello", "controller");
    }
}
