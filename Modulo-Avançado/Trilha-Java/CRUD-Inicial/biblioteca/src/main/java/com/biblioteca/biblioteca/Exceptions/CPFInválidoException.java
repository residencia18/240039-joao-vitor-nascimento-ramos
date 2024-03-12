package com.biblioteca.biblioteca.Exceptions;

public class CPFInválidoException extends Exception {
	
	public CPFInválidoException(String msg) {
		super(msg);
	}
	
	public CPFInválidoException() {
		super("CPF inválido!");
	}

}
