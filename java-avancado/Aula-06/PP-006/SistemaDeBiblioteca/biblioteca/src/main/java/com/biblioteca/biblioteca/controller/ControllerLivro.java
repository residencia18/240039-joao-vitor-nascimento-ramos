package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/livros/")
public class ControllerLivro {
    private static final Logger log = LoggerFactory.getLogger(ControllerLivro.class);

    @Autowired
    private LivroRepository repositoryLivro;

    @Autowired
    private AutorRepository repositoryAutor;

    @Autowired
    private EditoraRepository repositoryEditora;

    @GetMapping
    public List<LivroDTO> buscarTodosLivros() {
        log.info("[READ] Todos os livros foram pesquisados.");
        return repositoryLivro.findAll().stream().map(LivroDTO::new).collect(Collectors.toList());
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            Livro livro = repositoryLivro.getReferenceById(id);
            LivroDTO livroDTO = new LivroDTO(livro);
            log.info("[READ] Livro pesquisado: {}", livro);
            return ResponseEntity.ok(livroDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao buscar livro de id %d - Livro não encontrado : %s", id, e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao buscar livro: %s", e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }
    
	private String extrairDetalheErro(String mensagemErro) {
	    if (mensagemErro.contains("(editora_id)")) {
	        return "A editora especificada não foi encontrada.";
	    } else {
	        return "Erro ao criar empréstimo: " + mensagemErro;
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
            log.info("[CREATE] Livro criado: {}", livro);
            return ResponseEntity.created(uri).body(livroDTO);
        } catch(DataIntegrityViolationException e) {
	        String detalheErro = extrairDetalheErro(e.getMessage());
	        log.error("[CREATE] Erro ao criar livro: {}", detalheErro);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	    }catch (Exception e) {
	    	log.error("CREATE] Erro ao criar livro: {} ", e.getMessage());
	    	return ResponseEntity.badRequest().body(e.getMessage());  
	    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LivroFORM livroForm) {
        try {
            Livro livroAntes = repositoryLivro.getReferenceById(id); 
            Livro livro = new Livro(livroAntes); 
            construindoLivro(livroForm, livro); 
            repositoryLivro.save(livro); 
            LivroDTO livroDTO = new LivroDTO(livro);

            log.info("[UPDATE] Livro antes da atualização: {} | Livro atualizado: {}", livroAntes, livro);

            return ResponseEntity.ok(livroDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao atualizar livro de id %d - Livro não encontrado : %s", id, e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch(DataIntegrityViolationException e) {
	        String detalheErro = extrairDetalheErro(e.getMessage());
	        log.error("[UPDATE] Erro ao atualizar livro: {}", detalheErro);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	    }catch (Exception e) {
	    	log.error("UPDATE] Erro ao atualizar livro: {} ", e.getMessage());
	    	return ResponseEntity.badRequest().body(e.getMessage());  
	    }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Livro livro = repositoryLivro.getReferenceById(id);
            repositoryLivro.delete(livro);
            LivroDTO livroDTO = new LivroDTO(livro);
            log.info("[DELETE] Livro excluído: {}", livro);
            return ResponseEntity.ok(livroDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao excluir livro de id %d - Livro não encontrado : %s", id, e.getMessage());
            log.error("[DELETE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao excluir livro: %s", e.getMessage());
            log.error("[DELETE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
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
