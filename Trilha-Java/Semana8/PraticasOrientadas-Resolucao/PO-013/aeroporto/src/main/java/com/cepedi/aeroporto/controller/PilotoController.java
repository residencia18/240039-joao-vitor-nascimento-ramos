package com.cepedi.aeroporto.controller;

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

import com.cepedi.aeroporto.controller.DTO.PilotoDTO;
import com.cepedi.aeroporto.controller.form.PilotoFORM;
import com.cepedi.aeroporto.model.Piloto;
import com.cepedi.aeroporto.repository.PilotoRepository;

@RestController
@RequestMapping("/pilotos/")
public class PilotoController {

	@Autowired
	private PilotoRepository pilotoRepository;

	@GetMapping
	public List<PilotoDTO> buscaPilotos(String nome) {
		System.out.println(nome);
		return (nome != null)
				? pilotoRepository.findByNome(nome).stream().map(PilotoDTO::new).collect(Collectors.toList())
				: pilotoRepository.findAll().stream().map(PilotoDTO::new).collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<PilotoDTO> inserir(@RequestBody PilotoFORM pilotoForm , UriComponentsBuilder uribuilder) {
		Piloto piloto = pilotoForm.toPiloto();
		pilotoRepository.save(piloto);
		PilotoDTO pilotoDTO = new PilotoDTO(piloto);
		uribuilder.path("/pilotos/{id}");
		URI uri = uribuilder.buildAndExpand(piloto.getId()).toUri();
		return ResponseEntity.created(uri).body(pilotoDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listaUsuarios(@PathVariable Integer id,
			UriComponentsBuilder uriBuilder) {
		try {
			
			Piloto piloto = pilotoRepository.getReferenceById(id);
			PilotoDTO pilotoDTO = new PilotoDTO(piloto);
			uriBuilder.path("/piloto/{id}");
			return ResponseEntity.ok(pilotoDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alteraUsuario(@PathVariable Integer id,
			@RequestBody PilotoFORM pilotoform){
		try {
			Piloto piloto = pilotoRepository.getReferenceById(id);
			piloto.setNome(pilotoform.getNome());
			piloto.setNumBreve(pilotoform.getNumBreve());
			pilotoRepository.save(piloto);
			PilotoDTO usuarioDTO = new PilotoDTO(piloto);
			return ResponseEntity.ok(usuarioDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaUsuario(@PathVariable Integer id) {
	    try {
	        Piloto piloto = pilotoRepository.getReferenceById(id);
	        PilotoDTO pilotoDTO = new PilotoDTO(piloto);
	        pilotoRepository.delete(piloto);
	        return ResponseEntity.ok(pilotoDTO);
	    } catch (Exception e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	

}
