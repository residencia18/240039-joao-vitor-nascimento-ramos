package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.biblioteca.controller.DTO.AutorDTO;
import com.biblioteca.biblioteca.controller.FORM.AutorFORM;
import com.biblioteca.biblioteca.controller.repository.AutorRepository;
import com.biblioteca.biblioteca.model.Autor;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/autores/")
public class ControllerAutor {
    
    public static final Logger log = LoggerFactory.getLogger(ControllerAutor.class);

    @Autowired
    AutorRepository repository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AutorFORM autorForm, UriComponentsBuilder uriBuilder) {
        try {
            Autor autor = new Autor();
            construindoAutor(autorForm, autor);
            repository.save(autor);
            AutorDTO autorDTO = new AutorDTO(autor);
            uriBuilder.path("/autores/{id}");
            URI uri = uriBuilder.buildAndExpand(autor.getId()).toUri();
            log.info("[CREATE] Autor criado: {}", autor);
            return ResponseEntity.created(uri).body(autorDTO);
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao criar autor: %s", e.getMessage());
            log.error("[CREATE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }
    
    @GetMapping
    public List<AutorDTO> readAll() {
        log.info("[READ] Todos autores pesquisados");
        return repository.findAll().stream().map(AutorDTO::new).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            Autor autor = repository.getReferenceById(id);
            AutorDTO autorDTO = new AutorDTO(autor);
            
            log.info("[READ] Autor pesquisado: {}", autor);
            
            return ResponseEntity.ok(autorDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao buscar autor de id %d - Autor não encontrado : %s", id, e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao buscar autor de id %d - %s", id, e.getMessage());
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AutorFORM autorForm) {
        try {
            Autor autorAntes = repository.getReferenceById(id); 
            Autor autor = new Autor(autorAntes); 
            construindoAutor(autorForm, autor);
            repository.save(autor); 
            AutorDTO autorDTO = new AutorDTO(autor);

            log.info("[UPDATE] Autor antes da atualização: {} | Autor atualizado: {}", autorAntes, autor);

            return ResponseEntity.ok(autorDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao atualizar autor de id %d - Autor não encontrado : %s", id, e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao atualizar autor de id %d - %s", id, e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Autor autor = repository.getReferenceById(id);
            repository.delete(autor);
            AutorDTO autorDTO = new AutorDTO(autor);
            log.info("[DELETE] Autor excluído: {}", autor);
            return ResponseEntity.ok(autorDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao excluir autor de id %d - Autor não encontrado: {}", id, e.getMessage());
            log.error("[DELETE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao excluir autor: {}", e.getMessage());
            log.error("[DELETE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

    private void construindoAutor(AutorFORM autorForm, Autor autor) {
        autor.setNome(autorForm.nome());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        ErrorResponse errorResponse = new ErrorResponse(errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
