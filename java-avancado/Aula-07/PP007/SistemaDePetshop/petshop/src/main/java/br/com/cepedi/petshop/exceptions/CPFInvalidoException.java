package br.com.cepedi.petshop.exceptions;

public class CPFInvalidoException extends Exception{
	
	public CPFInvalidoException(String msg) {
		super(msg);
	}
	
	public CPFInvalidoException() {
		super("CPF inválido!");
	}

}
