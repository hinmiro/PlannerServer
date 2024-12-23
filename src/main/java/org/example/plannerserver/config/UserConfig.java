package org.example.plannerserver.config;

import org.example.plannerserver.entity.ApplicationData;
import org.example.plannerserver.entity.User;
import org.example.plannerserver.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

//    DUMMY DATA FOR DATABASE
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository) {
        return args -> {
            User tester = new User("Tester1", "test", "asd@ads.fi");
            User admin = new User("ADMINM", "root", "admin@planner.com");
            tester.setApplicationData(new ApplicationData());
            admin.setApplicationData(new ApplicationData());

            repository.saveAll(List.of(tester, admin));
        };
    }
}
