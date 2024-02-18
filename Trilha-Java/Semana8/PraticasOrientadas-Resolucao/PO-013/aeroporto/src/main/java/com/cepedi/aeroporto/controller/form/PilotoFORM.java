package com.cepedi.aeroporto.controller.form;

import com.cepedi.aeroporto.model.Piloto;

public class PilotoFORM {
	
	private String nome;
	private String numBreve;
	
	
	public PilotoFORM(String nome, String numBreve) {
		super();
		this.nome = nome;
		this.numBreve = numBreve;
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
	
	
	public Piloto toPiloto() {
		Piloto piloto = new Piloto();
		piloto.setNome(nome);
		piloto.setNumBreve(numBreve);
		return piloto;
	}
	
	

}
