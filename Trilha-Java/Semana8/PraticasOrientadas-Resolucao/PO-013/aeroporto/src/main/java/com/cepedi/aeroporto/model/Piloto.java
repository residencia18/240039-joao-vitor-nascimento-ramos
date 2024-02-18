package com.cepedi.aeroporto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PILOTO")
public class Piloto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="NOME")
	private String nome;
	@Column(name="NUM_BREVE")
	private String numBreve;
	
	public Piloto(String nome, String numBreve) {
		super();
		this.id = null;
		this.nome = nome;
		this.numBreve = numBreve;
	}


	public Piloto() {
		
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

	public String getNumBreve() {
		return numBreve;
	}

	public void setNumBreve(String numBreve) {
		this.numBreve = numBreve;
	}

	@Override
	public String toString() {
		return "Piloto [id=" + id + ", nome=" + nome + ", numBreve=" + numBreve + "]";
	}
	
	
	
	
	

}
