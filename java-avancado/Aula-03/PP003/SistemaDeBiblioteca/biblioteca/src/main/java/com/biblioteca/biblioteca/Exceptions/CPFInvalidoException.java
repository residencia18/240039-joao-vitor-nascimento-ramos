package com.biblioteca.biblioteca.Exceptions;

public class CPFInvalidoException extends Exception{
	
	public CPFInvalidoException(String msg) {
		super(msg);
	}
	
	public CPFInvalidoException() {
		super("CPF inválido!");
	}

}
