package com.app.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.app.web")
public class Bodas1Application {

	public static void main(String[] args) {
		SpringApplication.run(Bodas1Application.class, args);
	}

}
