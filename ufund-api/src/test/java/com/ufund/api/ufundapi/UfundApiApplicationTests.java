package com.ufund.api.ufundapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class UfundApiApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
        assertTrue(port > 0);
    }

}
