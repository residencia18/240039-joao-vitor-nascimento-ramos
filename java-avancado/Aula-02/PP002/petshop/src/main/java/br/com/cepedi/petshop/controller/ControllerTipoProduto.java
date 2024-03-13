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

import br.com.cepedi.petshop.controller.DTO.TipoProdutoDTO;
import br.com.cepedi.petshop.controller.FORM.TipoProdutoFORM;
import br.com.cepedi.petshop.controller.repository.TipoProdutoRepository;
import br.com.cepedi.petshop.model.TipoProduto;

@RestController
@RequestMapping("/produtos/tipos/")
public class ControllerTipoProduto {
    
    @Autowired
    TipoProdutoRepository tipoProdutoRepository;
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoProdutoFORM tipoProdutoForm , UriComponentsBuilder uriBuilder){
        try {
            TipoProduto tipoProduto = tipoProdutoForm.toTipoProduto();
            tipoProdutoRepository.save(tipoProduto);
            TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProduto);
            uriBuilder.path("/produtos/tipos/{id}");
            URI uri = uriBuilder.buildAndExpand(tipoProduto.getId()).toUri();
            return ResponseEntity.created(uri).body(tipoProdutoDTO);
        }catch(Exception e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public List<TipoProdutoDTO> readAll(){
        return tipoProdutoRepository.findAll().stream().map(TipoProdutoDTO::new).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoProdutoDTO> readById(@PathVariable Long id){
        Optional<TipoProduto> tipoProdutoOpcional = tipoProdutoRepository.findById(id);
        if(tipoProdutoOpcional.isPresent()) {
            TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProdutoOpcional.get());
            return ResponseEntity.ok(tipoProdutoDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody TipoProdutoFORM tipoProdutoForm){
        try {
            Optional<TipoProduto> tipoProdutoOpcional = tipoProdutoRepository.findById(id);
            if(tipoProdutoOpcional.isPresent()) {
                TipoProduto tipoProduto = tipoProdutoOpcional.get();
                tipoProduto.setNome(tipoProdutoForm.getNome());
                tipoProdutoRepository.save(tipoProduto);
                TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProduto);
                return ResponseEntity.ok(tipoProdutoDTO);
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
            Optional<TipoProduto> tipoProdutoOpcional = tipoProdutoRepository.findById(id);
            if(tipoProdutoOpcional.isPresent()) {
                TipoProduto tipoProduto = tipoProdutoOpcional.get();
                tipoProdutoRepository.delete(tipoProduto);
                TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO(tipoProduto);
                return ResponseEntity.ok(tipoProdutoDTO);
            } else {
                return ResponseEntity.notFound().build();        
            }
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
