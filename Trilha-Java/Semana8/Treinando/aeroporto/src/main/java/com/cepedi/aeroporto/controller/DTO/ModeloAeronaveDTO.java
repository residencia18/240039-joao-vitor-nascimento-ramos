package com.cepedi.aeroporto.controller.DTO;

import com.cepedi.aeroporto.model.ModeloAeronave;

public class ModeloAeronaveDTO {

	private String nome;
	private String fabricante;
	
	
	public ModeloAeronaveDTO(String nome, String fabricante) {
		super();
		this.nome = nome;
		this.fabricante = fabricante;
	}
	
	public ModeloAeronaveDTO(ModeloAeronave modeloAeronave) {
		this.nome = modeloAeronave.getNome();
		this.fabricante = modeloAeronave.getFabricante();
	}

	public String getNome() {
		return nome;
	}

	public String getFabricante() {
		return fabricante;
	}
	
	
	
	
	
}
