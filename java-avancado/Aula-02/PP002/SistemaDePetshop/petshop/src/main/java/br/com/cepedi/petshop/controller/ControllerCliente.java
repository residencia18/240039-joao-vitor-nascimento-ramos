package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.biblioteca.biblioteca.controller.ErrorResponse;
import com.biblioteca.biblioteca.controller.FORM.ClienteFORM;

import br.com.cepedi.petshop.controller.DTO.ClienteDTO;
import br.com.cepedi.petshop.controller.repository.ClienteRepository;
import br.com.cepedi.petshop.exceptions.CPFInvalidoException;
import br.com.cepedi.petshop.exceptions.NomeInvalidoException;
import br.com.cepedi.petshop.model.Cliente;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes/")public class ControllerCliente {
	@Autowired
	ClienteRepository clientesrepository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid ClienteFORM clienteForm , UriComponentsBuilder uriBuilder){
	    try {
	        Cliente cliente = new Cliente();
	        construindoCliente(clienteForm, cliente);
	        clientesrepository.save(cliente);
	        ClienteDTO clienteDTO = new ClienteDTO(cliente);
	        uriBuilder.path("/clientes/{id}");
	        URI uri = uriBuilder.buildAndExpand(cliente.getId()).toUri();
	        return ResponseEntity.created(uri).body(clienteDTO);
	    }catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	private void construindoCliente(ClienteFORM clienteForm, Cliente cliente)
			throws NomeInvalidoException, CPFInvalidoException {
		cliente.setNome(clienteForm.nome());
		cliente.setCpf(clienteForm.cpf());
	}
	
	@GetMapping
	public List<ClienteDTO> readAll() {
	    return clientesrepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> readById(@PathVariable Long id) {
	    Optional<Cliente> clienteOptional = clientesrepository.findById(id);
	    if (clienteOptional.isPresent()) {
	    	ClienteDTO ClienteDTO = new ClienteDTO(clienteOptional.get());
	        return ResponseEntity.ok(ClienteDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id , @RequestBody @Valid ClienteFORM clienteForm){
		try {
			Cliente cliente = clientesrepository.getReferenceById(id);
	        construindoCliente(clienteForm, cliente);
			clientesrepository.save(cliente);
			ClienteDTO clienteDTO = new ClienteDTO(cliente);
			return ResponseEntity.ok(clienteDTO);
		}catch(EmptyResultDataAccessException e ) {
			return ResponseEntity.notFound().build();
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			Cliente cliente = clientesrepository.getReferenceById(id);
			clientesrepository.delete(cliente);
			ClienteDTO clienteDTO = new ClienteDTO(cliente);
			return ResponseEntity.ok(clienteDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
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
	