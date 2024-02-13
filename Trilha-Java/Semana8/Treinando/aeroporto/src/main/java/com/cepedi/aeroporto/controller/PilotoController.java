package com.cepedi.aeroporto.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
