package br.com.cepedi.exceptions.motorista;

public class CNHMotoristaInvalidaException extends Exception{
	
	public CNHMotoristaInvalidaException() {
		super("CNH inválida");
	}
	
	public CNHMotoristaInvalidaException(String msg) {
		super(msg);
	}
	
}
