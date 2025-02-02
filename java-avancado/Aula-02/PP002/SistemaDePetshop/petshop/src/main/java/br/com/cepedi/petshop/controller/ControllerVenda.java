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

import br.com.cepedi.petshop.controller.DTO.VendaDTO;
import br.com.cepedi.petshop.controller.FORM.VendaFORM;
import br.com.cepedi.petshop.controller.repository.ClienteRepository;
import br.com.cepedi.petshop.controller.repository.VendaRepository;
import br.com.cepedi.petshop.model.Cliente;
import br.com.cepedi.petshop.model.Venda;

@RestController
@RequestMapping("/vendas/")
public class ControllerVenda {

    @Autowired
    private VendaRepository vendaRepository;
    
	@Autowired
	ClienteRepository clientesrepository;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody VendaFORM vendaForm, UriComponentsBuilder uriBuilder) {
	    try {
	        Venda venda = new Venda();
	        Optional<Cliente> clienteOptional = clientesrepository.findById(vendaForm.idCliente());
	        if (clienteOptional.isPresent()) {
	            Cliente cliente = clienteOptional.get();
	            construindoVenda(venda, cliente);
	            vendaRepository.save(venda);
	            VendaDTO vendaDTO = new VendaDTO(venda);
	            uriBuilder.path("/vendas/{id}");
	            URI uri = uriBuilder.buildAndExpand(venda.getId()).toUri();
	            return ResponseEntity.created(uri).body(vendaDTO);
	        } else {
                return ResponseEntity.badRequest().body("Cliente não encontrado!");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	// Na venda só é possivel atualizar o cliente, o valor é pelo venda Produto
	private void construindoVenda(Venda venda, Cliente cliente) {
		venda.setCliente(cliente);
	}


    @GetMapping
    public List<VendaDTO> readAll() {
        return vendaRepository.findAll().stream().map(VendaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> readById(@PathVariable Long id) {
        Optional<Venda> vendaOptional = vendaRepository.findById(id);
        if (vendaOptional.isPresent()) {
            Venda venda = vendaOptional.get();
            VendaDTO vendaDTO = new VendaDTO(venda);
            return ResponseEntity.ok().body(vendaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VendaFORM vendaForm) {
        try {
            Optional<Venda> vendaOptional = vendaRepository.findById(id);
            if (vendaOptional.isPresent()) {
                Venda venda = vendaOptional.get();
                Optional<Cliente> clienteOptional = clientesrepository.findById(vendaForm.idCliente());
                if (clienteOptional.isPresent()) {
                    Cliente cliente = clienteOptional.get();
                    construindoVenda(venda, cliente);
                    vendaRepository.save(venda);
                    VendaDTO vendaDTO = new VendaDTO(venda);
                    return ResponseEntity.ok().body(vendaDTO);
                } else {
                    return ResponseEntity.badRequest().body("Cliente não encontrado!");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Optional<Venda> vendaOptional = vendaRepository.findById(id);
            if (vendaOptional.isPresent()) {
                Venda venda = vendaOptional.get();
                vendaRepository.delete(venda);
                VendaDTO vendaDTO = new VendaDTO(venda);
                return ResponseEntity.ok(vendaDTO);
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
