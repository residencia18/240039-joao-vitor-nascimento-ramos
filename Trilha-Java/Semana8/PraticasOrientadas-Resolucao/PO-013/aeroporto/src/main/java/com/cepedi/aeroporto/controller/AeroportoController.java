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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cepedi.aeroporto.controller.DTO.AeroportoDTO;
import com.cepedi.aeroporto.controller.form.AeroportoFORM;
import com.cepedi.aeroporto.model.Aeroporto;
import com.cepedi.aeroporto.repository.AeroportoRepository;

@RestController
@RequestMapping("/aeroportos/")

public class AeroportoController {

	@Autowired
	private AeroportoRepository aeroportoRepository;

	@GetMapping
	public List<AeroportoDTO> bucaAeroportos(String icao, String nome) {
		return (icao == null)
				? (nome == null)
						? aeroportoRepository.findAll().stream().map(AeroportoDTO::new).collect(Collectors.toList())
						: aeroportoRepository.findByNome(nome).stream().map(AeroportoDTO::new)
								.collect(Collectors.toList())
				: (nome == null)
						? aeroportoRepository.findByICAO(icao).stream().map(AeroportoDTO::new)
								.collect(Collectors.toList())
						: aeroportoRepository.findByNomeAndICAO(nome, icao).stream().map(AeroportoDTO::new)
								.collect(Collectors.toList());

	}
	
	@PostMapping
	public ResponseEntity<AeroportoDTO> inserir(@RequestBody AeroportoFORM aeroportoFORM , UriComponentsBuilder uribuilder) {
		Aeroporto aeroporto = aeroportoFORM.toAeroporto();
		aeroportoRepository.save(aeroporto);
		AeroportoDTO aeroportoDTO = new AeroportoDTO(aeroporto);
		uribuilder.path("/modelosaeronaves/{id}");
		URI uri = uribuilder.buildAndExpand(aeroporto.getId()).toUri();
		return ResponseEntity.created(uri).body(aeroportoDTO);
	}
	


}
