package com.biblioteca.biblioteca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {
	
	public static final Logger log = LoggerFactory.getLogger(BibliotecaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
		log.info("Appication started...");
	}

}
