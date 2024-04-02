package com.biblioteca.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.controller.repository.LivroRepository;
import com.biblioteca.biblioteca.model.Cliente;
import com.biblioteca.biblioteca.model.Livro;
import com.github.javafaker.Faker;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private EditoraService editoraService;

    private Faker faker = new Faker();

    public void popularLivros(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Livro livro = new Livro();
            livro.setNome(faker.book().title());
            livro.setAutor(autorService.obterAutorAleatorio());
            livro.setEditora(editoraService.obterEditoraAleatoria());
            livroRepository.save(livro);
        }
    }
    
    public Livro  obterAleatorio() {
        long total = livroRepository.count();
        int indiceAleatorio = faker.number().numberBetween(0, (int) total);
        return livroRepository.findAll().get(indiceAleatorio);
    }
    
}