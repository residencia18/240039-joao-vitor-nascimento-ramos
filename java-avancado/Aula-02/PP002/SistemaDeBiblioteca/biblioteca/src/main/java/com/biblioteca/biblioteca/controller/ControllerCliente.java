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

import com.biblioteca.biblioteca.controller.DTO.ClienteDTO;
import com.biblioteca.biblioteca.controller.FORM.ClienteFORM;
import com.biblioteca.biblioteca.controller.repository.ClienteRepository;
import com.biblioteca.biblioteca.model.Cliente;

@RestController
@RequestMapping("/clientes/")
public class ControllerCliente {
	@Autowired
	ClienteRepository clientesrepository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ClienteFORM clienteForm , UriComponentsBuilder uriBuilder){
	    try {
	        Cliente cliente = clienteForm.toCliente();
	        clientesrepository.save(cliente);
	        ClienteDTO clienteDTO = new ClienteDTO(cliente);
	        uriBuilder.path("/clientes/{id}");
	        URI uri = uriBuilder.buildAndExpand(cliente.getId()).toUri();
	        return ResponseEntity.created(uri).body(clienteDTO);
	    }catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
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
	public ResponseEntity<?> update(@PathVariable Long id , @RequestBody ClienteFORM clienteForm){
		try {
			Cliente cliente = clientesrepository.getReferenceById(id);
			cliente.setNome(clienteForm.getNome());
			cliente.setCpf(clienteForm.getCpf());
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

}
