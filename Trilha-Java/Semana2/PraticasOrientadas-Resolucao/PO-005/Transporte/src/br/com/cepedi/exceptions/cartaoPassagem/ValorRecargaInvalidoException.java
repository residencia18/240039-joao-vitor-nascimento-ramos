package br.com.cepedi.exceptions.cartaoPassagem;

public class ValorRecargaInvalidoException extends Exception{
	
	public ValorRecargaInvalidoException() {
		super("Valor de recarga inválido");
	}
	
	public ValorRecargaInvalidoException(String msg) {
		super(msg);
	}

}
