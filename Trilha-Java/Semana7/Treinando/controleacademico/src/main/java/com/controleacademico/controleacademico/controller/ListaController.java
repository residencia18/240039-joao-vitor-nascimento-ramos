package com.controleacademico.controleacademico.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controleacademico.controleacademico.controller.DTO.UserDTO;
import com.controleacademico.controleacademico.model.Usuario;
import com.controleacademico.controleacademico.repository.UsuarioRepository;

@Controller
public class ListaController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;


	@RequestMapping("/listausuarios")
	@ResponseBody
	public ArrayList<UserDTO> listaUsuarios(){
		
		List<Usuario> listaUsuarios = (ArrayList<Usuario>) usuarioRepository.findAll();
		List<UserDTO> lista = new ArrayList<UserDTO>();
		for(Usuario u : listaUsuarios) {
			lista.add(new UserDTO(u));
		}
		
		return (ArrayList<UserDTO>) lista;
	}
	
}
