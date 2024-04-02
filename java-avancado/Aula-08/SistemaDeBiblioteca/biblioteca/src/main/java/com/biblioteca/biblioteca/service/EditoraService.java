package com.biblioteca.biblioteca.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.controller.repository.EditoraRepository;
import com.biblioteca.biblioteca.model.Editora;
import com.github.javafaker.Faker;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    private Faker faker = new Faker();

    public void popularEditoras(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Editora editora = new Editora();
            editora.setNome(faker.book().publisher());
            editoraRepository.save(editora);
        }
    }
    
    public Editora obterEditoraAleatoria() {
        long totalEditoras = editoraRepository.count();
        int indiceAleatorio = faker.number().numberBetween(0, (int) totalEditoras);
        return editoraRepository.findAll().get(indiceAleatorio);
    }
    
    public List<Long> recuperarTodosIds() {
        return editoraRepository.findAll().stream()
                .map(Editora::getId)
                .collect(Collectors.toList());
    }
    

    
}