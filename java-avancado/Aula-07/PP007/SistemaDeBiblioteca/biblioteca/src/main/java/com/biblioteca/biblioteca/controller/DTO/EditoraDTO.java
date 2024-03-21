package com.biblioteca.biblioteca.controller.DTO;

import com.biblioteca.biblioteca.model.Editora;

public class EditoraDTO {

	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public EditoraDTO(Editora editora) {
		this.nome = editora.getNome();
	}
}
