package org.example.plannerserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = "SECRET_KEY=your_test_secret_key")
class PlannerServerApplicationTests {

    @Test
    void contextLoads() {
    }

}
