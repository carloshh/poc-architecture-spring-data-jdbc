package com.github.carloshh.poc;

import com.github.carloshh.poc.test.IntegrationTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest extends IntegrationTestContext {

	@Test
	@DisplayName("Application context should load")
	void contextLoads() {
		assertThat(true).isTrue();
	}

}
