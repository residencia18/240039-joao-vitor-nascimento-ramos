package com.cepedi.aeroporto.controller.form;

import com.cepedi.aeroporto.model.ModeloAeronave;

public class ModeloAeronaveFORM {
	
	String nome;
	String fabricante;
	
	
	public ModeloAeronaveFORM(String nome, String fabricante) {
		super();
		this.nome = nome;
		this.fabricante = fabricante;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public ModeloAeronave toModeloAeronave() {
		ModeloAeronave modelo = new ModeloAeronave();
		modelo.setNome(nome);
		modelo.setFabricante(fabricante);
		return modelo;
	}
	

}
