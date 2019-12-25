package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.example.demo.mapper")
@SpringBootTest
class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Test
//	void contextLoads() {
//	}

}
