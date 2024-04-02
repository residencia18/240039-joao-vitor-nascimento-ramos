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

import br.com.cepedi.petshop.controller.DTO.VendaDTO;
import br.com.cepedi.petshop.controller.FORM.VendaFORM;
import br.com.cepedi.petshop.controller.repository.ClienteRepository;
import br.com.cepedi.petshop.controller.repository.VendaRepository;
import br.com.cepedi.petshop.model.Cliente;
import br.com.cepedi.petshop.model.Venda;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/vendas/")
public class ControllerVenda {

    @Autowired
    private VendaRepository vendaRepository;
    
	@Autowired
	ClienteRepository clientesrepository;
	
    private static final Logger log = LoggerFactory.getLogger(ControllerVenda.class);


	@PostMapping
    public ResponseEntity<?> create(@RequestBody VendaFORM vendaForm, UriComponentsBuilder uriBuilder) {
        try {
            Venda venda = new Venda();
            construindoVenda(vendaForm, venda);
            vendaRepository.save(venda);
            VendaDTO vendaDTO = new VendaDTO(venda);
            URI uri = uriBuilder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();
            log.info("[CREATE] Venda criada: {}", venda);
            return ResponseEntity.created(uri).body(vendaDTO);
        } catch (DataIntegrityViolationException e) {
            String detalheErro = extrairDetalheErro(e.getMessage());
            log.error("[CREATE] Erro ao criar venda: {}", detalheErro);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
        } catch (Exception e) {
            log.error("[CREATE] Erro ao criar venda: {} ", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	private String extrairDetalheErro(String mensagemErro) {
	    if (mensagemErro.contains("(cliente_id)")) {
	        return "O cliente especificado não foi encontrado.";
	    } else {
	        return "Erro ao criar venda: " + mensagemErro;
	    }
	}
	
	private void construindoVenda(VendaFORM vendaForm , Venda venda) {
		venda.setCliente(clientesrepository.getReferenceById(vendaForm.idCliente()));
	}

    @GetMapping
    public List<VendaDTO> readAll() {
        log.info("[READ] Todos as vendas pesquisados.");
        return vendaRepository.findAll().stream().map(VendaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            Venda venda = vendaRepository.getReferenceById(id);
            VendaDTO vendaDTO = new VendaDTO(venda);
            log.info("[READ] Venda recuperada: {}", venda);
            return ResponseEntity.ok().body(vendaDTO);
        } catch (EntityNotFoundException e) {
            log.error("[READ] Venda não encontrada com ID: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[READ] Erro ao buscar venda: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VendaFORM vendaForm) {
        try {
            Venda venda = vendaRepository.getReferenceById(id);
            construindoVenda(vendaForm, venda);
            vendaRepository.save(venda);
            VendaDTO vendaDTO = new VendaDTO(venda);
            return ResponseEntity.ok().body(vendaDTO);
        } catch (EntityNotFoundException e) {
            log.error("[UPDATE] Erro ao atualizar venda - Venda não encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            String detalheErro = extrairDetalheErro(e.getMessage());
            log.error("[UPDATE] Erro ao atualizar venda: {}", detalheErro);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
        } catch (Exception e) {
            log.error("[UPDATE] Erro ao atualizar venda: {} ", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Venda venda = vendaRepository.getReferenceById(id);
            vendaRepository.delete(venda);
            VendaDTO vendaDTO = new VendaDTO(venda);
            log.info("[DELETE] Venda excluída: {}", venda);
            return ResponseEntity.ok(vendaDTO);
        } catch (EntityNotFoundException e) {
            log.error("[DELETE] Erro ao excluir venda - Venda não encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[DELETE] Erro ao excluir venda: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
