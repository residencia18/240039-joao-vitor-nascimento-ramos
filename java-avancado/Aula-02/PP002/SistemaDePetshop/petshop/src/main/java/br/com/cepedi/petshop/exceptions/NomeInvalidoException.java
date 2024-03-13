package br.com.cepedi.petshop.exceptions;

public class NomeInvalidoException extends Exception{
	
	public NomeInvalidoException() {
		super("O Nome não deve conter números");
	}

}
