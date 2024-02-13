package com.cepedi.redesocial.controller.usuarios;

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

import com.cepedi.redesocial.controller.DTO.UsuarioDTO;
import com.cepedi.redesocial.controller.form.UsuarioFORM;
import com.cepedi.redesocial.model.Usuario;
import com.cepedi.redesocial.model.reposity.UsuarioRepository;

@RestController
@RequestMapping("/usuarios/")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<UsuarioDTO> queryUsuarios(String name) {
		return  (name!=null) ? usuarioRepository.findByName(name).stream().map(UsuarioDTO::new).collect(Collectors.toList()) : 
			usuarioRepository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioFORM usuarioForm , UriComponentsBuilder uribuilder) {
		Usuario usuario = usuarioForm.toUsuario();
		usuarioRepository.save(usuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		uribuilder.path("/usuarios/{id}");
		URI uri = uribuilder.buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
	
	


	
}
