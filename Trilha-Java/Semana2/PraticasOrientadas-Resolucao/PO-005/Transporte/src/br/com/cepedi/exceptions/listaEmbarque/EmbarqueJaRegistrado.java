package br.com.cepedi.exceptions.listaEmbarque;

public class EmbarqueJaRegistrado extends Exception {

	public EmbarqueJaRegistrado() {
		super("Esse embarque já foi realizado");
	}
	
}
