package com.biblioteca.biblioteca.controller.DTO;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.model.Cliente;

public class ClienteDTO {
	
	private String nome;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public ClienteDTO(Cliente cliente) {
		this.nome = cliente.getNome();
	}

}
