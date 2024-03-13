package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.model.Editora;

public class EditoraFORM {
	
	private String nome;

	public EditoraFORM(String nome) {
		super();
		this.nome = nome;
	}

	public EditoraFORM() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Editora toEditora() {
		Editora editora = new Editora();
		editora.setNome(nome);
		return editora;
	}
	

}
