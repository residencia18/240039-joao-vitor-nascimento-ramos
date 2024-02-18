package com.cepedi.aeroporto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="MODELO_AERONAVE")
public class ModeloAeronave {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;
	@Column(name="NOME")
	private String nome;
	@Column(name="FABRICANTE")
	private String fabricante;

	
	
	public ModeloAeronave( String nome, String fabricante) {
		super();
		this.id = null;
		this.fabricante = fabricante;
		this.nome = nome;
	}

	public ModeloAeronave() {
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFabricante() {
		return fabricante;
	}


	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String toString() {
		return "ModeloAeronave [id=" + id + ", nome=" + nome + ", fabricante=" + fabricante + "]";
	}
	
	
	
	
}
