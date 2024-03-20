package com.biblioteca.biblioteca.controller.DTO;

import com.biblioteca.biblioteca.model.Livro;


public class LivroDTO {
	
	private String nome;
	private String nomeAutor;
	private String nomeEditora;
	
	

	
	public LivroDTO(Livro livro) {
		this.nome = livro.getNome();
		this.nomeAutor = livro.getAutor().getNome();
		this.nomeEditora = livro.getEditora().getNome();
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	
	
	
	
	
	

	



}
