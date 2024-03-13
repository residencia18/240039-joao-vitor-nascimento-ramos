package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.model.Autor;

public class AutorFORM {
	
	private String nome;

	public AutorFORM(String nome) {
		super();
		this.nome = nome;
	}

	public AutorFORM() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Autor toAutor() {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

}
