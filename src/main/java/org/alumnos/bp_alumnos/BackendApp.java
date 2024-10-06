package org.alumnos.bp_alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BackendApp {
	public static void main(String[] args) {
		SpringApplication.run(BackendApp.class, args);
	}

}
