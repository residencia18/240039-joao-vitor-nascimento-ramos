package com.biblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="EDITORA")
public class Editora {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(name="NOME" , nullable = false)
	private String nome;
	
	
	
	public Editora() {
		super();
	}

	public Editora(Editora editora) {
		this.nome = editora.getNome();
		this.id = editora.getId();
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
		return "Editora [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
	

}
