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

import com.biblioteca.biblioteca.controller.DTO.AutorDTO;
import com.biblioteca.biblioteca.controller.FORM.AutorFORM;
import com.biblioteca.biblioteca.controller.repository.AutorRepository;
import com.biblioteca.biblioteca.model.Autor;


@RestController
@RequestMapping("/autores/")
public class ControllerAutor {

	@Autowired
	AutorRepository repository;
	

	
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AutorFORM autorForm, UriComponentsBuilder uriBuilder) {
        try {
            Autor autor = autorForm.toAutor();
            repository.save(autor);
            AutorDTO autorDTO = new AutorDTO(autor);
            uriBuilder.path("/autores/{id}");
            URI uri = uriBuilder.buildAndExpand(autor.getId()).toUri();
            return ResponseEntity.created(uri).body(autorDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	@GetMapping
	public List<AutorDTO> readAll() {
	    return repository.findAll().stream().map(AutorDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AutorDTO> readById(@PathVariable Long id) {
	    Optional<Autor> autorOptional = repository.findById(id);
	    if (autorOptional.isPresent()) {
	        AutorDTO autorDTO = new AutorDTO(autorOptional.get());
	        return ResponseEntity.ok(autorDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AutorFORM autorForm) {
	    try {
	        Autor autor = repository.getReferenceById(id);
	        if (autorForm.getNome() == null || autorForm.getNome().trim().isEmpty()) {
	            throw new IllegalArgumentException("Nome do autor é obrigatório");
	        }
	        autor.setNome(autorForm.getNome());
	        repository.save(autor);
	        AutorDTO autorDTO = new AutorDTO(autor);
	        return ResponseEntity.ok(autorDTO);
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.notFound().build();
	    }catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
	    try {
	        Autor autor = repository.getReferenceById(id);
	        repository.delete(autor);
	        AutorDTO autorDTO = new AutorDTO(autor);
	        return ResponseEntity.ok(autorDTO);
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
 
	


	
	
}
