package com.cepedi.redesocial.controller.DTO;

import com.cepedi.redesocial.model.Usuario;

public class UsuarioDTO {

	
	private String name;
	private String email;
	
	public UsuarioDTO(Usuario usuario) {
		this.name = usuario.getName();
		this.email = usuario.getEmail();
	}
	
	
	
	public UsuarioDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}



	public UsuarioDTO() {
		
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	
	
}
