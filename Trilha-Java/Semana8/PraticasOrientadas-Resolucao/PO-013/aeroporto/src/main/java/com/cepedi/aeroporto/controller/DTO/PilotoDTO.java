package com.cepedi.aeroporto.controller.DTO;

import com.cepedi.aeroporto.model.Piloto;

public class PilotoDTO {


	private String nome;

	
	public PilotoDTO(String nome) {
		this.nome = nome;
	}

	public PilotoDTO(Piloto piloto) {
		this.nome = piloto.getNome();
	}
	


	public String getNome() {
		return nome;
	}
	
	
	
}
