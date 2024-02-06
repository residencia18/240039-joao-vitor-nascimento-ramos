package br.com.cepedi.exceptions.listaVeiculos;

public class PlacaJaCadastradaException extends Exception{
	
	public PlacaJaCadastradaException() {
		super("Essa placa já foi cadastrada.");
	}
	
	public PlacaJaCadastradaException(String msg) {
		super(msg);
	}
}
