package br.com.cepedi.petshop.controller;

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

import com.biblioteca.biblioteca.controller.ErrorResponse;

import br.com.cepedi.petshop.controller.DTO.MarcaDTO;
import br.com.cepedi.petshop.controller.FORM.MarcaFORM;
import br.com.cepedi.petshop.controller.repository.MarcaRepository;
import br.com.cepedi.petshop.model.Marca;

@RestController
@RequestMapping("/produtos/marcas/")
public class ControllerMarca {

    @Autowired
    private MarcaRepository marcaRepository;
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MarcaFORM marcaForm, UriComponentsBuilder uriBuilder){
    	try {
    		Marca marca = new Marca();
    		construindoMarca(marcaForm , marca);
    		marcaRepository.save(marca);
    		MarcaDTO marcaDTO = new MarcaDTO(marca);
    		uriBuilder.path("/produtos/marcas/{id}");
    		URI uri = uriBuilder.buildAndExpand(marca.getId()).toUri();
    		return ResponseEntity.created(uri).body(marcaDTO);
    	}catch(Exception e ) {
    		return ResponseEntity.badRequest().build();
    	}
    }
    
    public void construindoMarca(MarcaFORM marcaForm , Marca marca) {
    	marca.setNome(marcaForm.nome());
    }
    
    @GetMapping
    public List<MarcaDTO> readAll(){
    	return marcaRepository.findAll().stream().map(MarcaDTO::new).collect(Collectors.toList());
    	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> readById(@PathVariable Long id) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        
        if (marcaOptional.isPresent()) {
            MarcaDTO marcaDTO = new MarcaDTO(marcaOptional.get());
            return ResponseEntity.ok(marcaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MarcaFORM marcaForm){
        try {
            Optional<Marca> marcaOptional = marcaRepository.findById(id);     
            if (marcaOptional.isPresent()) {
                Marca marca = marcaOptional.get();
        		construindoMarca(marcaForm , marca);
                marcaRepository.save(marca);     
                MarcaDTO marcaDTO = new MarcaDTO(marca);
                return ResponseEntity.ok(marcaDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            Optional<Marca> marcaOptional = marcaRepository.findById(id);
            if (marcaOptional.isPresent()) {
                Marca marca = marcaOptional.get();  
                marcaRepository.delete(marca);   
                MarcaDTO marcaDTO = new MarcaDTO(marca);
                return ResponseEntity.ok(marcaDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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