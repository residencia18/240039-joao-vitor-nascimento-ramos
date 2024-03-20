package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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

import br.com.cepedi.petshop.controller.DTO.PagamentoDTO;
import br.com.cepedi.petshop.controller.FORM.PagamentoFORM;
import br.com.cepedi.petshop.controller.repository.PagamentoRepository;
import br.com.cepedi.petshop.controller.repository.TipoPagamentoRepository;
import br.com.cepedi.petshop.controller.repository.VendaRepository;
import br.com.cepedi.petshop.model.Pagamento;
import br.com.cepedi.petshop.model.TipoPagamento;
import br.com.cepedi.petshop.model.Venda;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/pagamentos/")
public class ControllerPagamento {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;

	@Autowired
	private VendaRepository vendaRepository;

	private static final Logger log = LoggerFactory.getLogger(ControllerPagamento.class);

	@PostMapping
	public ResponseEntity<?> create(@RequestBody PagamentoFORM pagamentoForm, UriComponentsBuilder uriBuilder) {
		try {

			TipoPagamento tipoPagamento = tipoPagamentoRepository.getReferenceById(pagamentoForm.idTipoPagamento());

			Venda venda = vendaRepository.getReferenceById(pagamentoForm.idVenda());

			Optional<Pagamento> pagamentoExistente = pagamentoRepository.findByVenda(venda);
			if (pagamentoExistente.isPresent()) {
				return ResponseEntity.badRequest().body("Já existe um pagamento associado a esta venda!");
			}

			Pagamento pagamento = new Pagamento();
			construindoPagamento(tipoPagamento, venda, pagamento);
			pagamentoRepository.save(pagamento);

			URI uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

			return ResponseEntity.created(uri).body(new PagamentoDTO(pagamento));
		} catch (DataIntegrityViolationException e) {
			String detalheErro = extrairDetalheErro(e.getMessage());
			log.error("[CREATE] Erro ao criar pagamento: {}", detalheErro);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
		} catch (Exception e) {
			log.error("[CREATE] Erro ao criar pagamento: {} ", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private String extrairDetalheErro(String mensagemErro) {
		if (mensagemErro.contains("(venda_id)")) {
			return "A Venda especificada não foi encontrada.";
		} else if (mensagemErro.contains("(tipo_pagamento_id)")) {
			return "O tipo de pagamento especificado não foi encontrado.";
		} else {
			return "Erro ao criar venda: " + mensagemErro;
		}
	}

	private void construindoPagamento(TipoPagamento tipoPagamento, Venda venda, Pagamento pagamento) {
		pagamento.setTipoPagamento(tipoPagamento);
		pagamento.setVenda(venda);
	}

	@GetMapping
	public List<PagamentoDTO> readAll() {
		log.info("[READ] Todos os pagamentos pesquisados.");
		return pagamentoRepository.findAll().stream().map(PagamentoDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {
		try {
			Pagamento pagamento = pagamentoRepository.getReferenceById(id);
			PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento);
			log.info("[READ] Pagamento recuperado: {}", pagamento);
			return ResponseEntity.ok().body(pagamentoDTO);
		} catch (EntityNotFoundException e) {
			log.error("[READ] Pagamento não encontrado com ID: {}", id);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("[READ] Erro ao buscar pagamento: {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PagamentoFORM pagamentoForm) {
		try {

			TipoPagamento tipoPagamento = tipoPagamentoRepository.getReferenceById(pagamentoForm.idTipoPagamento());

			Venda venda = vendaRepository.getReferenceById(pagamentoForm.idVenda());

			Pagamento pagamento = pagamentoRepository.getReferenceById(id);

			construindoPagamento(tipoPagamento, venda, pagamento);

			pagamentoRepository.save(pagamento);
			PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento);
			return ResponseEntity.ok().body(pagamentoDTO);

		} catch (EntityNotFoundException e) {
			log.error("[UPDATE] Erro ao atualizar pagamento - Pagamento não encontrado: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			String detalheErro = extrairDetalheErro(e.getMessage());
			log.error("[UPDATE] Erro ao atualizar pagamento: {}", detalheErro);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
		} catch (Exception e) {
			log.error("[UPDATE] Erro ao atualizar pagamento: {} ", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
	    try {
	        Pagamento pagamento = pagamentoRepository.getReferenceById(id);
	        pagamentoRepository.delete(pagamento);
	        PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento);
	        log.info("[DELETE] Pagamento excluído: {}", pagamento);
	        return ResponseEntity.ok(pagamentoDTO);
	    } catch (EntityNotFoundException e) {
	        log.error("[DELETE] Erro ao excluir pagamento - Pagamento não encontrado: {}", e.getMessage());
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        log.error("[DELETE] Erro ao excluir pagamento: {}", e.getMessage());
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

}
