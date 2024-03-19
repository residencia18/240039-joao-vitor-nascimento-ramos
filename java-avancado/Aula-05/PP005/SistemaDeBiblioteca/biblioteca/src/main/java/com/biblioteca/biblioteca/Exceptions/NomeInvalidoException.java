package com.biblioteca.biblioteca.Exceptions;

public class NomeInvalidoException extends Exception{
	
	public NomeInvalidoException() {
		super("O Nome não deve conter números");
	}

}
