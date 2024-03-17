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

import br.com.cepedi.petshop.controller.DTO.TipoPagamentoDTO;
import br.com.cepedi.petshop.controller.FORM.TipoPagamentoFORM;
import br.com.cepedi.petshop.controller.repository.TipoPagamentoRepository;
import br.com.cepedi.petshop.model.TipoPagamento;

@RestController
@RequestMapping("/pagamentos/tipos/")
public class ControllerTipoPagamento {
	
	@Autowired
	TipoPagamentoRepository tipoPagamentoRepository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody TipoPagamentoFORM tipoPagamentoForm , UriComponentsBuilder uriBuilder){
		try {
			TipoPagamento tipoPagamento = new TipoPagamento();
			construindoTipoPagamento(tipoPagamentoForm, tipoPagamento);
			tipoPagamentoRepository.save(tipoPagamento);
			TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
			uriBuilder.path("/pagamentos/tipos/{id}");
			URI uri = uriBuilder.buildAndExpand(tipoPagamento.getId()).toUri();
			return ResponseEntity.created(uri).body(tipoPagamentoDTO);
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private void construindoTipoPagamento(TipoPagamentoFORM tipoPagamentoForm, TipoPagamento tipoPagamento) {
		tipoPagamento.setNome(tipoPagamentoForm.nome());
	}
	
	@GetMapping
	public List<TipoPagamentoDTO> readAll(){
		return tipoPagamentoRepository.findAll().stream().map(TipoPagamentoDTO::new).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoPagamentoDTO> readById(@PathVariable Long id){
		Optional<TipoPagamento> tipoPagamentoOpcional = tipoPagamentoRepository.findById(id);
		if(tipoPagamentoOpcional.isPresent()) {
			TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamentoOpcional.get());
			return ResponseEntity.ok(tipoPagamentoDTO);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id , @RequestBody TipoPagamentoFORM tipoPagamentoForm){
	    try {
	        Optional<TipoPagamento> tipoPagamentoOpcional = tipoPagamentoRepository.findById(id);
	        if(tipoPagamentoOpcional.isPresent()) {
	            TipoPagamento tipoPagamento = tipoPagamentoOpcional.get();
				construindoTipoPagamento(tipoPagamentoForm, tipoPagamento);
	            tipoPagamentoRepository.save(tipoPagamento);
	            TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
	            return ResponseEntity.ok(tipoPagamentoDTO);
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
	        Optional<TipoPagamento> tipoPagamentoOpcional = tipoPagamentoRepository.findById(id);
	        if(tipoPagamentoOpcional.isPresent()) {
	            TipoPagamento tipoPagamento = tipoPagamentoOpcional.get();
	            tipoPagamentoRepository.delete(tipoPagamento);
	            TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
	            return ResponseEntity.ok(tipoPagamentoDTO);
	        } else {
	            return ResponseEntity.notFound().build();		
	        }
		}catch(Exception e) {
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
