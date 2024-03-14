package com.biblioteca.biblioteca.controller.DTO;

import com.biblioteca.biblioteca.model.Autor;

public class AutorDTO {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AutorDTO(Autor autor) {
		this.nome = autor.getNome();
	}

}
