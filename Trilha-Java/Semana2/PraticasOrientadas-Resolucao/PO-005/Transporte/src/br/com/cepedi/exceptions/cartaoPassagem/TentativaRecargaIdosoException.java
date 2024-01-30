package br.com.cepedi.exceptions.cartaoPassagem;

public class TentativaRecargaIdosoException extends Exception{
	
	public TentativaRecargaIdosoException() {
		super("Não é permitido realizar recarga no cartao de idoso");
	}
	
	public TentativaRecargaIdosoException(String msg) {
		super(msg);
	}

}
