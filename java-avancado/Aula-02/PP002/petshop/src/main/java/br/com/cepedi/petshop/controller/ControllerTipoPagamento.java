package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
			TipoPagamento tipoPagamento = tipoPagamentoForm.toTipoPagamento();
			tipoPagamentoRepository.save(tipoPagamento);
			TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
			uriBuilder.path("/pagamentos/tipos/{id}");
			URI uri = uriBuilder.buildAndExpand(tipoPagamento.getId()).toUri();
			return ResponseEntity.created(uri).body(tipoPagamentoDTO);
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
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
	            tipoPagamento.setNome(tipoPagamentoForm.getNome());
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
	
	
	
}
