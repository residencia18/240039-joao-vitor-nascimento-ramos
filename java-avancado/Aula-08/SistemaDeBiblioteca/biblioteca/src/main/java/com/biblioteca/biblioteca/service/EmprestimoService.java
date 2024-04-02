package com.biblioteca.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.controller.repository.ClienteRepository;
import com.biblioteca.biblioteca.controller.repository.EmprestimoRepository;
import com.biblioteca.biblioteca.controller.repository.LivroRepository;
import com.biblioteca.biblioteca.model.Emprestimo;
import com.github.javafaker.Faker;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;


    @Autowired
    private LivroService livroService;
    
    @Autowired
    private ClienteService clienteService;



    public void popularEmprestimos(int quantidade) {

        for (int i = 0; i < quantidade; i++) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setCliente(clienteService.obterAleatorio());
            emprestimo.setLivro(livroService.obterAleatorio());
            emprestimo.setData_emprestimo(LocalDateTime.now());
            emprestimoRepository.save(emprestimo);
        }
    }


}