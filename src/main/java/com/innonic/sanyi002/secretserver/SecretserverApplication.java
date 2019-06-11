package com.innonic.sanyi002.secretserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.innonic.sanyi002.secretserver.repository")
public class SecretserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretserverApplication.class, args);
	}

}
