package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.model.Livro;

public class LivroFORM {
	
	private String nome;
	private Long id_autor;
	private Long id_editora;
	
	
	public LivroFORM(String nome, Long id_autor , Long id_editora) {
		this.nome = nome;
		this.id_autor = id_autor;
		this.id_editora = id_editora;
	}
	
	public LivroFORM() {
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId_autor() {
		return id_autor;
	}

	public void setId_autor(Long id_autor) {
		this.id_autor = id_autor;
	}

	public Long getId_editora() {
		return id_editora;
	}

	public void setId_editora(Long id_editora) {
		this.id_editora = id_editora;
	}

	

	


}
