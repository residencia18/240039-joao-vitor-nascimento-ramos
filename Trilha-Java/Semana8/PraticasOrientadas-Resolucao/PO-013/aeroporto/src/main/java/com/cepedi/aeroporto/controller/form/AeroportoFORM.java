package com.cepedi.aeroporto.controller.form;

import com.cepedi.aeroporto.model.Aeroporto;

public class AeroportoFORM {
	
	private String nome;
	private String ICAO;
	
	
	public AeroportoFORM(String nome, String iCAO) {
		super();
		this.nome = nome;
		ICAO = iCAO;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getICAO() {
		return ICAO;
	}
	public void setICAO(String iCAO) {
		ICAO = iCAO;
	}
	
	
	public Aeroporto toAeroporto() {
		Aeroporto aeroporto = new Aeroporto();
		aeroporto.setNome(nome);
		aeroporto.setICAO(ICAO);
		return aeroporto;
	}
	
	

}
