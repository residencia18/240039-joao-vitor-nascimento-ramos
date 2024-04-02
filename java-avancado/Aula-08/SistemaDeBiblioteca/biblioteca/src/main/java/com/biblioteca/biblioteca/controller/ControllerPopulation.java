package com.biblioteca.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.biblioteca.Exceptions.CPFInvalidoException;
import com.biblioteca.biblioteca.Exceptions.NomeInvalidoException;
import com.biblioteca.biblioteca.service.AutorService;
import com.biblioteca.biblioteca.service.ClienteService;
import com.biblioteca.biblioteca.service.EditoraService;
import com.biblioteca.biblioteca.service.EmprestimoService;
import com.biblioteca.biblioteca.service.LivroService;

@RestController
@RequestMapping("/populacao")
public class ControllerPopulation {

    @Autowired
    private AutorService autorService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private EditoraService editoraService;
    
    @Autowired
    private LivroService serviceLivro;
    
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/autores")
    public String popularAutores() {
        autorService.popularAutores(10); 
        return "Autores populados com sucesso!";
    }
    
    @PostMapping("/clientes")
    public String popularClientes() throws NomeInvalidoException, CPFInvalidoException {
        clienteService.popularClientes(10); // Popula 10 clientes
        return "Clientes populados com sucesso!";
    }

   @PostMapping("/editoras")
   public String popularEditoras() {
        editoraService.popularEditoras(10); // Popula 10 editoras
      return "Editoras populadas com sucesso!";
    }
    
    @PostMapping("/livros")
    public String popularLivros() {
    	serviceLivro.popularLivros(10); // Popula 10 editoras
        return "Livros populadas com sucesso!";
    }
    
    @PostMapping("/emprestimos")
    public String popularEmprestimos() {
    	emprestimoService.popularEmprestimos(10); // Popula 10 editoras
        return "Emprestimos populadas com sucesso!";
    }
    
}
    
