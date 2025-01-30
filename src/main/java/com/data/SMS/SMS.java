package com.data.SMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SMS {

	public static void main(String[] args) {
		SpringApplication.run(SMS.class, args);
	}
}
