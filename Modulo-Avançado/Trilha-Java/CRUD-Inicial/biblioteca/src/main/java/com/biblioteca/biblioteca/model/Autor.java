package com.biblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="AUTOR")
public class Autor {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;
	@Column(name="NAME")
	private String nome;
	
	
	public Autor() {
		super();
	}


	public Autor(String nome) {
		super();
		this.nome = nome;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
