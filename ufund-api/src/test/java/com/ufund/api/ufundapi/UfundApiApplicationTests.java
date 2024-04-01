package com.ufund.api.ufundapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UfundApiApplicationTests {

	@MockBean
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
		// Verify that the application context loads successfully
		assertThat(SpringApplication.run(UfundApiApplication.class)).isNotNull();
	}
}
