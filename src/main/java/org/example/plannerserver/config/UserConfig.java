package org.example.plannerserver.config;

import org.example.plannerserver.dto.UserDTO;
import org.example.plannerserver.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

//    DUMMY DATA FOR DATABASE
    @Bean
    CommandLineRunner userCommandLineRunner(AuthenticationService service) {
        return args -> {
            service.registerUser(new UserDTO("Tester1", "test", "asd@ads.fi"));
            service.registerUser(new UserDTO("ADMIN", "root", "admin@planner.com"));
        };
    }
}
