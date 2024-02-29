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

import com.cepedi.aeroporto.controller.DTO.AeroportoDTO;
import com.cepedi.aeroporto.controller.DTO.ModeloAeronaveDTO;
import com.cepedi.aeroporto.controller.form.AeroportoFORM;
import com.cepedi.aeroporto.model.Aeroporto;
import com.cepedi.aeroporto.model.ModeloAeronave;
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
		uribuilder.path("/aeroportos/{id}");
		URI uri = uribuilder.buildAndExpand(aeroporto.getId()).toUri();
		return ResponseEntity.created(uri).body(aeroportoDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listaUsuarios(@PathVariable Integer id,
			UriComponentsBuilder uriBuilder) {
		try {
			
			Aeroporto aeroporto = aeroportoRepository.getReferenceById(id);
			AeroportoDTO aeroportoDTO = new AeroportoDTO(aeroporto);
			uriBuilder.path("/aeroportos/{id}");
			return ResponseEntity.ok(aeroportoDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alteraUsuario(@PathVariable Integer id,
			@RequestBody AeroportoFORM aeroportoForm){
		try {
			Aeroporto aeroporto = aeroportoRepository.getReferenceById(id);
			aeroporto.setNome(aeroportoForm.getNome());
			aeroporto.setICAO(aeroportoForm.getICAO());
			aeroportoRepository.save(aeroporto);
			AeroportoDTO aeroportoDTO = new AeroportoDTO(aeroporto);
			return ResponseEntity.ok(aeroportoDTO);
		}catch(Exception e ) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaUsuario(@PathVariable Integer id) {
	    try {
	    	Aeroporto aeroporto = aeroportoRepository.getReferenceById(id);
	    	AeroportoDTO aeroportoDTO = new AeroportoDTO(aeroporto);
	    	aeroportoRepository.delete(aeroporto);
	        return ResponseEntity.ok(aeroportoDTO);
	    } catch (Exception e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	


}
