package com.geeth.karma_test_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KarmaTestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarmaTestBackendApplication.class, args);
	}

}
