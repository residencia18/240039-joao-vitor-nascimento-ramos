package com.cepedi.aeroporto.controller.DTO;

import com.cepedi.aeroporto.model.Aeroporto;

public class AeroportoDTO {

	private String nome;
	
	public AeroportoDTO(String nome) {
		super();
		this.nome = nome;
	}

	
	public AeroportoDTO(Aeroporto aeroporto) {
		this.nome = aeroporto.getNome();
	}



	public String getNome() {
		return nome;
	}

	
	
}
