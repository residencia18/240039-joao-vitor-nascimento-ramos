package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
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

import br.com.cepedi.petshop.controller.DTO.TipoPagamentoDTO;
import br.com.cepedi.petshop.controller.FORM.TipoPagamentoFORM;
import br.com.cepedi.petshop.controller.repository.TipoPagamentoRepository;
import br.com.cepedi.petshop.model.TipoPagamento;

@RestController
@RequestMapping("/pagamentos/tipos/")
public class ControllerTipoPagamento {
    
    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;
    
    public static final Logger log = LoggerFactory.getLogger(ControllerTipoPagamento.class);
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoPagamentoFORM tipoPagamentoForm , UriComponentsBuilder uriBuilder){
        try {
            TipoPagamento tipoPagamento = tipoPagamentoForm.toTipoPagamento();
            tipoPagamentoRepository.save(tipoPagamento);
            TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
            uriBuilder.path("/pagamentos/tipos/{id}");
            URI uri = uriBuilder.buildAndExpand(tipoPagamento.getId()).toUri();
            log.info("[CREATE] Tipo de pagamento criado: {}", tipoPagamento);
            return ResponseEntity.created(uri).body(tipoPagamentoDTO);
        }catch(Exception e ) {
            log.error("[CREATE] Erro ao criar tipo de pagamento: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public List<TipoPagamentoDTO> readAll(){
        log.info("[READ] Todos tipos de pagamento pesquisados");
        return tipoPagamentoRepository.findAll().stream().map(TipoPagamentoDTO::new).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id){
        try {
            TipoPagamento tipoPagamento = tipoPagamentoRepository.getReferenceById(id);
            TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
            log.info("[READ] Tipo de pagamento encontrado: {}", tipoPagamento);
            return ResponseEntity.ok(tipoPagamentoDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[READ] Erro ao buscar tipo de pagamento de id {} - Tipo de pagamento não encontrado : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[READ] Erro ao buscar tipo de pagamento: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody TipoPagamentoFORM tipoPagamentoForm){
        try {
            TipoPagamento tipoPagamento = tipoPagamentoRepository.getReferenceById(id);
            tipoPagamento.setNome(tipoPagamentoForm.getNome());
            tipoPagamentoRepository.save(tipoPagamento);
            TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
            log.info("[UPDATE] Tipo de pagamento atualizado: {}", tipoPagamento);
            return ResponseEntity.ok(tipoPagamentoDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[UPDATE] Erro ao atualizar tipo de pagamento - Tipo de pagamento não encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[UPDATE] Erro ao atualizar tipo de pagamento: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            TipoPagamento tipoPagamento = tipoPagamentoRepository.getReferenceById(id);
            tipoPagamentoRepository.delete(tipoPagamento);
            TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO(tipoPagamento);
            log.info("[DELETE] Tipo de pagamento excluído: {}", tipoPagamento);
            return ResponseEntity.ok(tipoPagamentoDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[DELETE] Erro ao excluir tipo de pagamento - Tipo de pagamento não encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[DELETE] Erro ao excluir tipo de pagamento: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
