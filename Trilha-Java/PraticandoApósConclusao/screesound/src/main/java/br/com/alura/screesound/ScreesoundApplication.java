package br.com.alura.screesound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screesound.principal.Principal;
import br.com.alura.screesound.repository.ArtistaRepository;

@SpringBootApplication
public class ScreesoundApplication implements CommandLineRunner{
	
    @Autowired
    private ArtistaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ScreesoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal =  new Principal(repositorio);
		principal.exibeMenu();
		
	}

}
