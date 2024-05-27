package com.example.slang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SlangApplicationTests {

	@MockBean
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void contextLoads() {
	}

}
