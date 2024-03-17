package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.biblioteca.biblioteca.controller.DTO.LivroDTO;
import com.biblioteca.biblioteca.controller.FORM.LivroFORM;
import com.biblioteca.biblioteca.controller.repository.AutorRepository;
import com.biblioteca.biblioteca.controller.repository.EditoraRepository;
import com.biblioteca.biblioteca.controller.repository.LivroRepository;
import com.biblioteca.biblioteca.model.Livro;

@RestController
@RequestMapping("/livros/")
public class ControllerLivro {
	
	@Autowired
	private LivroRepository repositoryLivro;
	
	@Autowired
	private AutorRepository repositoryAutor;
	
	@Autowired
	private EditoraRepository repositoryEditora;
	
	@GetMapping
	public List<LivroDTO> buscarTodosLivros() {
	    return repositoryLivro.findAll().stream().map(LivroDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) {
	    Optional<Livro> livroOptional = repositoryLivro.findById(id);
	    if (livroOptional.isPresent()) {
			LivroDTO livroDTO = new LivroDTO(livroOptional.get());
	        return ResponseEntity.ok(livroDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody LivroFORM livroForm, UriComponentsBuilder uriBuilder) {
        try {
            Livro livro = new Livro();
            construindoLivro(livroForm, livro);
            repositoryLivro.save(livro);
            LivroDTO livroDTO = new LivroDTO(livro);
            uriBuilder.path("/livros/{id}");
            URI uri = uriBuilder.buildAndExpand(livro.getId()).toUri();
            return ResponseEntity.created(uri).body(livroDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


	
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LivroFORM livroForm) {
        try {
            Optional<Livro> livroOptional = repositoryLivro.findById(id);
            if (!livroOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Livro livro = livroOptional.get();
            construindoLivro(livroForm, livro);
            repositoryLivro.save(livro);
            LivroDTO livroDTO = new LivroDTO(livro);
            return ResponseEntity.ok(livroDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao atualizar o livro.");
        }
    }
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			Livro livro = repositoryLivro.getReferenceById(id);
			repositoryLivro.delete(livro);
			LivroDTO livroDTO = new LivroDTO(livro);
			return ResponseEntity.ok(livroDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	
	private void construindoLivro(LivroFORM livroForm, Livro livro) {
		livro.setNome(livroForm.nome());
		livro.setAutor(repositoryAutor.getReferenceById(livroForm.id_autor()));
		livro.setEditora(repositoryEditora.getReferenceById(livroForm.id_editora()));
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
