package com.example.dexture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DextureApplication {

	public static void main(String[] args) {
		SpringApplication.run(DextureApplication.class, args);
	}

}
