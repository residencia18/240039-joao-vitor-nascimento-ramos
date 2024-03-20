package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
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

import br.com.cepedi.petshop.controller.DTO.ProdutoDTO;
import br.com.cepedi.petshop.controller.FORM.ProdutoFORM;
import br.com.cepedi.petshop.controller.repository.MarcaRepository;
import br.com.cepedi.petshop.controller.repository.ProdutoRepository;
import br.com.cepedi.petshop.controller.repository.TipoProdutoRepository;
import br.com.cepedi.petshop.exceptions.PrecoInvalidoException;
import br.com.cepedi.petshop.model.Marca;
import br.com.cepedi.petshop.model.Produto;
import br.com.cepedi.petshop.model.TipoProduto;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/produtos/")
public class ControllerProduto {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	private static final Logger log = LoggerFactory.getLogger(ControllerProduto.class);

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProdutoFORM produtoForm, UriComponentsBuilder uriBuilder) {
		try {
			TipoProduto tipoProduto = tipoProdutoRepository.getReferenceById(produtoForm.idTipoProduto());
			Marca marca = marcaRepository.getReferenceById(produtoForm.idMarca());
			Produto produto = new Produto();
			construindoProduto(produtoForm, tipoProduto, marca, produto);
			produtoRepository.save(produto);
			ProdutoDTO produtoDTO = new ProdutoDTO(produto);
			URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
			return ResponseEntity.created(uri).body(produtoDTO);
		} catch (DataIntegrityViolationException e) {
			String detalheErro = extrairDetalheErro(e.getMessage());
			log.error("[CREATE] Erro ao criar produto: {}", detalheErro);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
		} catch (Exception e) {
			log.error("[CREATE] Erro ao criar produto: {} ", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private String extrairDetalheErro(String mensagemErro) {
		if (mensagemErro.contains("(marca_id)")) {
			return "A marca especificada não foi encontrada.";
		} else if (mensagemErro.contains("(tipo_produto_id)")) {
			return "O tipo de produto especificado não foi encontrado.";
		} else {
			return "Erro ao criar venda: " + mensagemErro;
		}
	}

	private void construindoProduto(ProdutoFORM produtoForm, TipoProduto tipoProduto, Marca marca, Produto produto)
			throws PrecoInvalidoException {
		atualizaProduto(produtoForm, produto, tipoProduto, marca);
	}

	@GetMapping
	public List<ProdutoDTO> readAll() {
		log.info("[READ] Todos os produtos pesquisados.");
		return produtoRepository.findAll().stream().map(ProdutoDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {

		try {
			Produto produto = produtoRepository.getReferenceById(id);
			ProdutoDTO produtoDTO = new ProdutoDTO(produto);
			log.info("[READ] Produto recuperado: {}", produto);
			return ResponseEntity.ok().body(produtoDTO);
		} catch (EntityNotFoundException e) {
			log.error("[READ] Produto não encontrado com ID: {}", id);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("[READ] Erro ao buscar produto: {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProdutoFORM produtoForm) {
		try {

			Produto produto = produtoRepository.getReferenceById(id);
			TipoProduto tipoProduto = tipoProdutoRepository.getReferenceById(produtoForm.idTipoProduto());
			Marca marca = marcaRepository.getReferenceById(produtoForm.idMarca());

			construindoProduto(produtoForm, tipoProduto, marca, produto);
			ProdutoDTO produtoDTO = new ProdutoDTO(produto);
			produtoRepository.save(produto);
			return ResponseEntity.ok().body(produtoDTO);

		} catch (EntityNotFoundException e) {
			log.error("[UPDATE] Erro ao atualizar produto - Produto não encontrado: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			String detalheErro = extrairDetalheErro(e.getMessage());
			log.error("[UPDATE] Erro ao atualizar produto: {}", detalheErro);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
		} catch (Exception e) {
			log.error("[UPDATE] Erro ao atualizar produto: {} ", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	private void atualizaProduto(ProdutoFORM produtoForm, Produto produto, TipoProduto tipoProdutoFORM, Marca marca)
			throws PrecoInvalidoException {
		produto.setNome(produtoForm.nome());
		produto.setPreco(produtoForm.preco());
		produto.setDescricao(produtoForm.descricao());
		produto.setTipoProduto(tipoProdutoFORM);
		produto.setMarca(marca);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
	    try {
	        Produto produto = produtoRepository.getReferenceById(id);
	        produtoRepository.delete(produto);
	        ProdutoDTO produtoDTO = new ProdutoDTO(produto);
	        log.info("[DELETE] Produto excluído: {}", produto);
	        return ResponseEntity.ok(produtoDTO);
	    } catch (EntityNotFoundException e) {
	        log.error("[DELETE] Erro ao excluir produto - Produto não encontrado: {}", e.getMessage());
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        log.error("[DELETE] Erro ao excluir produto: {}", e.getMessage());
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}


}