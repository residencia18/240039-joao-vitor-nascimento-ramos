package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.biblioteca.controller.DTO.EditoraDTO;
import com.biblioteca.biblioteca.controller.FORM.EditoraFORM;
import com.biblioteca.biblioteca.controller.repository.EditoraRepository;
import com.biblioteca.biblioteca.model.Editora;

@RestController
@RequestMapping("/editoras/")
public class ControllerEditora {

	
	@Autowired
	EditoraRepository editorarepository;
	
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EditoraFORM editoraForm, UriComponentsBuilder uriBuilder) {
        try {
            Editora editora = editoraForm.toEditora();
            editorarepository.save(editora);
            EditoraDTO editoraDTO = new EditoraDTO(editora);
            uriBuilder.path("/editoras/{id}");
            URI uri = uriBuilder.buildAndExpand(editora.getId()).toUri();
            return ResponseEntity.created(uri).body(editoraDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	@GetMapping
	public List<EditoraDTO> readAll() {
	    return editorarepository.findAll().stream().map(EditoraDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EditoraDTO> readById(@PathVariable Long id) {
	    Optional<Editora> editoraOptional = editorarepository.findById(id);
	    if (editoraOptional.isPresent()) {
	    	EditoraDTO autorDTO = new EditoraDTO(editoraOptional.get());
	        return ResponseEntity.ok(autorDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EditoraFORM editoraForm) {
        try {
            Editora editora = editorarepository.getReferenceById(id);
            editora.setNome(editoraForm.getNome());
            editorarepository.save(editora);
            EditoraDTO editoraDTO = new EditoraDTO(editora);
            return ResponseEntity.ok(editoraDTO);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Editora editora = editorarepository.getReferenceById(id);
            editorarepository.delete(editora);
            EditoraDTO editoraDTO = new EditoraDTO(editora);
            return ResponseEntity.ok(editoraDTO);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
}
