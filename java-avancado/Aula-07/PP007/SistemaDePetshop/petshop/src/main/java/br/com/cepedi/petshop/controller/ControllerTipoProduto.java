package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.cepedi.petshop.controller.DTO.TipoProdutoDTO;
import br.com.cepedi.petshop.controller.FORM.TipoProdutoFORM;
import br.com.cepedi.petshop.controller.repository.TipoProdutoRepository;
import br.com.cepedi.petshop.model.TipoProduto;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/produtos/tipos/")
public class ControllerTipoProduto {
    
    public static final Logger log = LoggerFactory.getLogger(ControllerTipoProduto.class);

    @Autowired
    TipoProdutoRepository tipoProdutoRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoProdutoFORM tipoProdutoForm, UriComponentsBuilder uriBuilder) {
        try {
            TipoProduto tipoProduto = new TipoProduto();
            construindoTipoProduto(tipoProdutoForm, tipoProduto);
            tipoProdutoRepository.save(tipoProduto);
            TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProduto);
            uriBuilder.path("/produtos/tipos/{id}");
            URI uri = uriBuilder.buildAndExpand(tipoProduto.getId()).toUri();
            log.info("[CREATE] Tipo de produto criado: {}", tipoProduto);
            return ResponseEntity.created(uri).body(tipoProdutoDTO);
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao criar tipo de produto: %s", e.getMessage());
            log.error("[CREATE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

	private void construindoTipoProduto(TipoProdutoFORM tipoProdutoForm, TipoProduto tipoProduto) {
		tipoProduto.setNome(tipoProdutoForm.nome());
	}

    @GetMapping
    public List<TipoProdutoDTO> readAll() {
        log.info("[READ] Todos tipos de produto pesquisados");
        return tipoProdutoRepository.findAll().stream().map(TipoProdutoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            TipoProduto tipoProduto = tipoProdutoRepository.getReferenceById(id);
            TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProduto);
            log.info("[READ] Tipo de produto encontrado: {}", tipoProduto);
            return ResponseEntity.ok(tipoProdutoDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao buscar tipo de produto de id %d - Tipo de produto não encontrado : %s", id, e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao buscar tipo de produto: %s", e.getMessage());
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TipoProdutoFORM tipoProdutoForm) {
        try {
            TipoProduto tipoProduto = tipoProdutoRepository.getReferenceById(id);
            construindoTipoProduto(tipoProdutoForm, tipoProduto);
            tipoProdutoRepository.save(tipoProduto);
            TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProduto);
            log.info("[UPDATE] Tipo de produto atualizado: {}", tipoProduto);
            return ResponseEntity.ok(tipoProdutoDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao atualizar tipo de produto - Tipo de produto não encontrado: %s", e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de produto não encontrado");
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao atualizar tipo de produto: %s", e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.badRequest().body("Erro ao atualizar tipo de produto: " + e.getMessage());
        }
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			TipoProduto tipoProduto = tipoProdutoRepository.getReferenceById(id);
			tipoProdutoRepository.delete(tipoProduto);
			TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProduto);
			log.info("[DELETE] Tipo de produto excluído: {}", tipoProduto);
			return ResponseEntity.ok(tipoProdutoDTO);
		} catch (EntityNotFoundException e) {
			log.error("[DELETE] Erro ao excluir tipo de produto - Tipo de produto não encontrado: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de produto não encontrado");
		} catch (Exception e) {
			log.error("[DELETE] Erro ao excluir tipo de produto: {}", e.getMessage());
			return ResponseEntity.badRequest().body("Erro ao excluir tipo de produto: " + e.getMessage());
		}
	}
}
