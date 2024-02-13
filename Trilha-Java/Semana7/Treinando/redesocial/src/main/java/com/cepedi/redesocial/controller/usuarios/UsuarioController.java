package com.cepedi.redesocial.controller.usuarios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cepedi.redesocial.controller.DTO.UsuarioDTO;
import com.cepedi.redesocial.model.reposity.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    @RequestMapping("/listausuarios/")
    @ResponseBody
	public List<UsuarioDTO> queryUsuarios(String name) {
		return usuarioRepository.findByName(name).stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
    
    @RequestMapping("/listausuarios")
    @ResponseBody
	public List<UsuarioDTO> queryUsuarios() {
		return usuarioRepository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
	
}
