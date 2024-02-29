package com.cepedi.leilao.controller;

import java.net.URI;
import java.util.List;
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

import com.cepedi.leilao.controller.DTO.LeilaoDTO;
import com.cepedi.leilao.controller.form.LeilaoFORM;
import com.cepedi.leilao.model.Leilao;
import com.cepedi.leilao.repository.LeilaoRepository;

@RestController
@RequestMapping("/leilao/")
public class LeilaoController {

    @Autowired
    private LeilaoRepository leilaoRepository;
    
    @GetMapping
    public List<LeilaoDTO> getLeiloes() {
        return leilaoRepository.findAll().stream().map(LeilaoDTO::new).collect(Collectors.toList());
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaConcorrente(@PathVariable Long id,
			UriComponentsBuilder uriBuilder) {
		try {	
	        Leilao leilao = leilaoRepository.getReferenceById(id);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			uriBuilder.path("/leilao/{id}");
			return ResponseEntity.ok(leilaoDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	

	
    @PostMapping
    public ResponseEntity<?> createLeilao(@RequestBody LeilaoFORM leilaoForm, UriComponentsBuilder uriBuilder) {
        Leilao leilao = leilaoForm.toLeilao();
        leilaoRepository.save(leilao);
        LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
        URI uri = uriBuilder.path("/leilao/{id}").buildAndExpand(leilao.getId()).toUri();
        return ResponseEntity.created(uri).body(leilaoDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> alteraLeilao(@PathVariable Long id, @RequestBody LeilaoFORM leilaoForm) {
        try {
            Leilao leilao = leilaoRepository.getReferenceById(id);
            leilao.setDescricao(leilaoForm.getDescricao());
            leilao.setValorMinimo(leilaoForm.getValorMinimo());
            leilao.setStatus(leilaoForm.getStatus());
            leilaoRepository.save(leilao);
            LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
            return ResponseEntity.ok(leilaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaLeilao(@PathVariable Long id) {
        try {
            Leilao leilao = leilaoRepository.getReferenceById(id);
            leilaoRepository.delete(leilao);
            LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
            return ResponseEntity.ok(leilaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    


    
}