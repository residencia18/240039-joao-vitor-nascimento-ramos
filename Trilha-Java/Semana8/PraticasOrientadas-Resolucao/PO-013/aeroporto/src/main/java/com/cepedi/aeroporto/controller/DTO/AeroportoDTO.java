package com.cepedi.aeroporto.controller.DTO;

import com.cepedi.aeroporto.model.Aeroporto;

public class AeroportoDTO {

	private String nome;
	private String ICAO;
	
	public AeroportoDTO(String nome) {
		super();
		this.nome = nome;
	}

	
	public AeroportoDTO(Aeroporto aeroporto) {
		this.nome = aeroporto.getNome();
		this.ICAO = aeroporto.getICAO();
	}



	public String getIcao() {
		return ICAO;
	}


	public String getNome() {
		return nome;
	}

	
	
}
