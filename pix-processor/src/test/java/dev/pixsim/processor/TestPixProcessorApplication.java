package dev.pixsim.processor;

import org.springframework.boot.SpringApplication;

public class TestPixProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.from(PixProcessorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
