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
	@Column(name="NAME" , nullable = false)
	private String nome;
	
	
    public Autor(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
    }
	
	
	public Autor() {
		super();
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
		
	    if (nome == null || nome.trim().isEmpty()) {
	        throw new IllegalArgumentException("Nome é obrigatório");
	    }
		
		this.nome = nome;
	}


	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
	
	

}
