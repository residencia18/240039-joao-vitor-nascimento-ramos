package br.com.cepedi.petshop.controller;

import java.math.BigDecimal;
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

import br.com.cepedi.petshop.controller.DTO.Venda_ProdutoDTO;
import br.com.cepedi.petshop.controller.FORM.VendaProdutoFORM;
import br.com.cepedi.petshop.controller.repository.PagamentoRepository;
import br.com.cepedi.petshop.controller.repository.ProdutoRepository;
import br.com.cepedi.petshop.controller.repository.VendaRepository;
import br.com.cepedi.petshop.controller.repository.Venda_ProdutoRepository;
import br.com.cepedi.petshop.model.Pagamento;
import br.com.cepedi.petshop.model.Produto;
import br.com.cepedi.petshop.model.Venda;
import br.com.cepedi.petshop.model.VendaProduto;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/vendas/{idVenda}/produtos/")
public class ControllerVendaProduto {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private Venda_ProdutoRepository vendaProdutoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	private static final Logger log = LoggerFactory.getLogger(ControllerVenda.class);

	@PostMapping
	public ResponseEntity<?> adicionarProdutoAVenda(@PathVariable Long idVenda,
			@RequestBody VendaProdutoFORM vendaProdutoForm, UriComponentsBuilder uriBuilder) {
		try {
			Venda venda = vendaRepository.getReferenceById(idVenda);

			Optional<Pagamento> pagamentoExistente = pagamentoRepository.findByVenda(venda);
			if (pagamentoExistente != null) {
				return ResponseEntity.badRequest()
						.body("Não é possível adicionar produtos a uma venda que já possui pagamento.");
			}

			Produto produto = produtoRepository.getReferenceById(vendaProdutoForm.idProduto());
			VendaProduto vendaProduto = new VendaProduto();
			vendaProduto.setVenda(venda);
			vendaProduto.setProduto(produto);
			vendaProduto.setQuantidade(vendaProdutoForm.quantidade());
			BigDecimal valor = produto.getPreco().multiply(new BigDecimal(vendaProdutoForm.quantidade()));
			venda.adicionarValor(valor);
			vendaRepository.save(venda);
			vendaProdutoRepository.save(vendaProduto);
			Venda_ProdutoDTO VendaProdutoDTO = new Venda_ProdutoDTO(vendaProduto);
			uriBuilder.path("/vendas/" + idVenda + "/produtos/{id}");
			URI uri = uriBuilder.buildAndExpand(vendaProduto.getId()).toUri();
			return ResponseEntity.created(uri).body(VendaProdutoDTO);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			String detalheErro = extrairDetalheErro(e.getMessage());
			log.error("[POST] Erro ao adicionar produto à venda {} : {}", idVenda ,  detalheErro);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detalheErro);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private String extrairDetalheErro(String mensagemErro) {
		if (mensagemErro.contains("(venda_id)")) {
			return "O produto especificado não foi encontrado nessa venda.";
		} else {
			return "Erro ao criar venda: " + mensagemErro;
		}
	}

	@GetMapping
	public ResponseEntity<?> findAll(@PathVariable Long idVenda) {
		try {
			Venda venda = vendaRepository.getReferenceById(idVenda);
			List<VendaProduto> produtos = vendaProdutoRepository.findByVendaId(idVenda);
			List<Venda_ProdutoDTO> produtosDTO = produtos.stream().map(Venda_ProdutoDTO::new)
					.collect(Collectors.toList());
			log.info("[READ] Todos os produtos da venda {} pesquisados.", idVenda);
			return ResponseEntity.ok(produtosDTO);
		} catch (Exception e) {
			log.error("[READ] Erro ao buscar produtos da venda {}: {}", idVenda, e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{produtoId}")
	public ResponseEntity<?> findById(@PathVariable Long idVenda, @PathVariable Long produtoId) {
		try {
			VendaProduto vendaProduto = vendaProdutoRepository.getReferenceById(produtoId);
			Venda_ProdutoDTO produtoDTO = new Venda_ProdutoDTO(vendaProduto);
			return ResponseEntity.ok(produtoDTO);
		} catch (Exception e) {
			log.error("[READ] Erro ao buscar produto da venda {}: {}", produtoId, e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{produtoId}")
	public ResponseEntity<?> update(@PathVariable Long idVenda, @PathVariable Long produtoId,
			@RequestBody VendaProdutoFORM vendaProdutoForm) {
		try {
			VendaProduto vendaProdutoAtual = vendaProdutoRepository.getReferenceById(produtoId);

			// Verifica se já existe um pagamento associado à venda
			Optional<Pagamento> pagamentoExistente = pagamentoRepository.findByVenda(vendaProdutoAtual.getVenda());
			if (pagamentoExistente != null) {
				return ResponseEntity.badRequest()
						.body("Não é possível atualizar produtos de uma venda que já possui pagamento.");
			}

			Produto produto = produtoRepository.getReferenceById(vendaProdutoForm.idProduto());
			Venda venda = vendaProdutoAtual.getVenda();

			// Retira o valor atual daquele venda produto do venda para adicionar o novo
			BigDecimal valorARetirar = vendaProdutoAtual.getProduto().getPreco()
					.multiply(new BigDecimal(vendaProdutoAtual.getQuantidade()));
			BigDecimal valorAdicionar = produto.getPreco().multiply(new BigDecimal(vendaProdutoForm.quantidade()));
			venda.retiraValor(valorARetirar);
			venda.adicionarValor(valorAdicionar);

			vendaProdutoAtual.setProduto(produto);
			vendaProdutoAtual.setQuantidade(vendaProdutoForm.quantidade());

			vendaRepository.save(venda);
			vendaProdutoRepository.save(vendaProdutoAtual);

			Venda_ProdutoDTO produtoDTO = new Venda_ProdutoDTO(vendaProdutoAtual);
			return ResponseEntity.ok(produtoDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{produtoId}")
	public ResponseEntity<?> delete(@PathVariable Long idVenda, @PathVariable Long produtoId) {
		try {
			Venda venda = vendaRepository.getReferenceById(idVenda);
			VendaProduto vendaProdutoAtual = vendaProdutoRepository.getReferenceById(produtoId);

			// Retira o valor atual do venda produto da venda
			BigDecimal valorARetirar = vendaProdutoAtual.getProduto().getPreco()
					.multiply(new BigDecimal(vendaProdutoAtual.getQuantidade()));
			venda.retiraValor(valorARetirar);

			vendaRepository.save(venda);
			vendaProdutoRepository.delete(vendaProdutoAtual);

			Venda_ProdutoDTO produtoDTO = new Venda_ProdutoDTO(vendaProdutoAtual);
			return ResponseEntity.ok(produtoDTO);
		} catch (EntityNotFoundException e) {
			log.error("[DELETE] Erro ao excluir produto da venda - Venda produto não encontrado: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			String detalheErro = extrairDetalheErro(e.getMessage());
			log.error("[DELETE] Erro ao excluir produto da venda: {}", detalheErro);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
		} catch (Exception e) {
			log.error("[DELETE] Erro ao excluir produto da venda: {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
