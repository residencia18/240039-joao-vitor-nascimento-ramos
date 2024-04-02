package com.biblioteca.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.controller.repository.AutorRepository;
import com.biblioteca.biblioteca.model.Autor;
import com.github.javafaker.Faker;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    private Faker faker = new Faker();

    public void popularAutores(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Autor autor = new Autor();
            autor.setNome(faker.book().author());
            autorRepository.save(autor);
        }
    }
    
    public Autor obterAutorAleatorio() {
        long totalAutores = autorRepository.count();
        int indiceAleatorio = faker.number().numberBetween(0, (int) totalAutores);
        return autorRepository.findAll().get(indiceAleatorio);
    }
    
    

    
}