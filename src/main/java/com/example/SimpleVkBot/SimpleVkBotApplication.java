package com.example.SimpleVkBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SimpleVkBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleVkBotApplication.class, args);
	}

}
