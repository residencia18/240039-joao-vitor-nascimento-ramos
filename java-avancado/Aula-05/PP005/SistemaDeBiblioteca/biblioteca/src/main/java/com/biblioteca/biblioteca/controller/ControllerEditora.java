package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import com.biblioteca.biblioteca.controller.DTO.EditoraDTO;
import com.biblioteca.biblioteca.controller.FORM.EditoraFORM;
import com.biblioteca.biblioteca.controller.repository.EditoraRepository;
import com.biblioteca.biblioteca.model.Editora;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/editoras/")
public class ControllerEditora {

    private static final Logger log = LoggerFactory.getLogger(ControllerEditora.class);

    @Autowired
    EditoraRepository editorarepository;
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid EditoraFORM editoraForm, UriComponentsBuilder uriBuilder) {
        try {
            Editora editora = new Editora();
            construindoEditora(editoraForm, editora);
            editorarepository.save(editora);
            EditoraDTO editoraDTO = new EditoraDTO(editora);
            uriBuilder.path("/editoras/{id}");
            URI uri = uriBuilder.buildAndExpand(editora.getId()).toUri();
            log.info("[CREATE] Editora criada: {}", editora);
            return ResponseEntity.created(uri).body(editoraDTO);
        } catch (Exception e) {
            log.error("[CREATE] Erro ao criar editora: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<EditoraDTO> readAll() {
        log.info("[READ] Todas editoras pesquisadas");
        return editorarepository.findAll().stream().map(EditoraDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            Editora editora = editorarepository.getReferenceById(id);
            EditoraDTO editoraDTO = new EditoraDTO(editora);
            log.info("[READ] Editora pesquisada: {}", editora);
            return ResponseEntity.ok(editoraDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[READ] Erro ao buscar editora de id {} - Editora não encontrada : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[READ] Erro ao buscar editora: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EditoraFORM editoraForm) {
        try {
            Editora editoraAntes = editorarepository.getReferenceById(id); 
            Editora editora = new Editora(editoraAntes); 
            construindoEditora(editoraForm, editora); 
            editorarepository.save(editora); 
            EditoraDTO editoraDTO = new EditoraDTO(editora);
            log.info("[UPDATE] Editora antes da atualização: {} | Editora atualizada: {}", editoraAntes, editora);
            return ResponseEntity.ok(editoraDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[UPDATE] Erro ao atualizar editora - Editora não encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[UPDATE] Erro ao atualizar editora: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Editora editora = editorarepository.getReferenceById(id);
            editorarepository.delete(editora);
            EditoraDTO editoraDTO = new EditoraDTO(editora);
            log.info("[DELETE] Editora excluída: {}", editora);
            return ResponseEntity.ok(editoraDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[DELETE] Erro ao excluir editora - Editora não encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[DELETE] Erro ao excluir editora: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }	
    
    
	private void construindoEditora(EditoraFORM editoraForm, Editora editora) {
		editora.setNome(editoraForm.nome());
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
