package br.com.cepedi.exceptions.veiculo;

public class montadoraVeiculoInvalidaException extends Exception{
	
	public montadoraVeiculoInvalidaException() {
		super("O nome da montadora deve possuir apenas letras e espaços");
	}
	
	public montadoraVeiculoInvalidaException(String msg) {
		super(msg);
	}

}
