package com.controleacademico.controleacademico.controller.DTO;

import com.controleacademico.controleacademico.model.Usuario;


public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	
	
	public UserDTO(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public UserDTO(Usuario u) {
		super();
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
	}
	
	
	

	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}
	

}
