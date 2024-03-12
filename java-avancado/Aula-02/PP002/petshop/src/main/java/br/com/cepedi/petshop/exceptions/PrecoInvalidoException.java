package br.com.cepedi.petshop.exceptions;

public class PrecoInvalidoException extends Exception{
	
	public PrecoInvalidoException() {
		super("O preço deve ser maior que zero!");
	}
	
	
}
