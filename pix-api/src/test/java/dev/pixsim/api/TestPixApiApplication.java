package dev.pixsim.api;

import org.springframework.boot.SpringApplication;

public class TestPixApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(PixApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
