package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.cepedi.petshop.controller.DTO.ClienteDTO;
import br.com.cepedi.petshop.controller.FORM.ClienteFORM;
import br.com.cepedi.petshop.controller.repository.ClienteRepository;
import br.com.cepedi.petshop.model.Cliente;

@RestController
@RequestMapping("/clientes/")
public class ControllerCliente {
	
    public static final Logger log = LoggerFactory.getLogger(ControllerCliente.class);

	
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
	        log.info("[CREATE] Cliente criado: {}", cliente);
	        return ResponseEntity.created(uri).body(clienteDTO);
	    }catch (Exception e) {
	        log.error("[CREATE] Erro ao criar cliente: {}", e.getMessage());
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@GetMapping
	public List<ClienteDTO> readAll() {
        log.info("[READ] Todos clientes pesquisados");
	    return clientesrepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            Cliente cliente = clientesrepository.getReferenceById(id);
            ClienteDTO ClienteDTO = new ClienteDTO(cliente);
            log.info("[READ] Cliente pesquisado: {}", cliente);
            return ResponseEntity.ok(ClienteDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[READ] Erro ao buscar cliente de id {} - Cliente não encontrado : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[READ] Erro ao buscar cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
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
			log.info("[UPDATE] Cliente atualizado: {}", cliente);
			return ResponseEntity.ok(clienteDTO);
		}catch(EmptyResultDataAccessException e ) {
			log.error("[UPDATE] Erro ao atualizar cliente - Cliente não encontrado: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		}catch(Exception e ) {
			log.error("[UPDATE] Erro ao atualizar cliente: {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			Cliente cliente = clientesrepository.getReferenceById(id);
			clientesrepository.delete(cliente);
			ClienteDTO clienteDTO = new ClienteDTO(cliente);
			log.info("[DELETE] Cliente excluído: {}", cliente);
			return ResponseEntity.ok(clienteDTO);
		}catch(Exception e ) {
			log.error("[DELETE] Erro ao excluir cliente: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}	
}
