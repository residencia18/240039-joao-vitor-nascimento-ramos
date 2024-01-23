package br.com.cepedi.exceptions.veiculo;

public class placaVeiculoInvalidaException  extends Exception{
	
	public placaVeiculoInvalidaException() {
		super("Placa invalida de veículo, formato correto : XXX-1111");
	}
	
	public placaVeiculoInvalidaException(String msg) {
		super(msg);
	}

}
