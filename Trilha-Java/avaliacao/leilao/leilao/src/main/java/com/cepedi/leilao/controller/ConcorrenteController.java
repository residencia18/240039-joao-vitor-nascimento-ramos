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

import com.cepedi.leilao.controller.DTO.ConcorrenteDTO;
import com.cepedi.leilao.controller.form.ConcorrenteFORM;
import com.cepedi.leilao.model.Concorrente;
import com.cepedi.leilao.repository.ConcorrenteRepository;

@RestController
@RequestMapping("/concorrente/")
public class ConcorrenteController {
	
	@Autowired
	private ConcorrenteRepository concorrenteRepository;
	
	@GetMapping
	public List<ConcorrenteDTO> buscaConcorrentes() {
		return concorrenteRepository.findAll().stream().map(ConcorrenteDTO::new).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaConcorrente(@PathVariable Integer id,
			UriComponentsBuilder uriBuilder) {
		try {
			
			Concorrente concorrente  = concorrenteRepository.getReferenceById(id);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			uriBuilder.path("/concorrente/{id}");
			return ResponseEntity.ok(concorrenteDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<ConcorrenteDTO> inserir(@RequestBody ConcorrenteFORM concorrenteForm , UriComponentsBuilder uribuilder) {
		Concorrente concorrente = concorrenteForm.toConcorrente();
		concorrenteRepository.save(concorrente);
		ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
		uribuilder.path("/concorrente/{id}");
		URI uri = uribuilder.buildAndExpand(concorrente.getId()).toUri();
		return ResponseEntity.created(uri).body(concorrenteDTO);
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<?> alteraConcorrente(@PathVariable Integer id, @RequestBody ConcorrenteFORM concorrenteForm) {
        try {
			Concorrente concorrente  = concorrenteRepository.getReferenceById(id);
            concorrente.setNome(concorrenteForm.getNome());
            concorrente.setCpf(concorrenteForm.getCpf());
            concorrenteRepository.save(concorrente);
            ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
            return ResponseEntity.ok(concorrenteDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaConcorrente(@PathVariable Integer id) {
        try {
			Concorrente concorrente  = concorrenteRepository.getReferenceById(id);
            concorrenteRepository.delete(concorrente);
            ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
            return ResponseEntity.ok(concorrenteDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
	

}
