package br.com.cepedi.exceptions.veiculo;

public class modeloVeiculoInvalidoException extends Exception{
	
	public modeloVeiculoInvalidoException() {
		super("Modelo inválido. Deve possuir apenas caracteres alfanumericos e espaços");
	}
	
	public modeloVeiculoInvalidoException(String msg) {
		super(msg);
	}

}
