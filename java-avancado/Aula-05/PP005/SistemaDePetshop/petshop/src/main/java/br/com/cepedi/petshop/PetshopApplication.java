package br.com.cepedi.petshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.biblioteca.biblioteca.BibliotecaApplication;

@SpringBootApplication
public class PetshopApplication {
	
	public static final Logger log = LoggerFactory.getLogger(PetshopApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PetshopApplication.class, args);
		log.info("Aplication run...");
	}

}
