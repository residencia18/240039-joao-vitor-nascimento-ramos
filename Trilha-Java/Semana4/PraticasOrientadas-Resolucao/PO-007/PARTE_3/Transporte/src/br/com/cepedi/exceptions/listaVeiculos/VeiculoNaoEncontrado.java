package br.com.cepedi.exceptions.listaVeiculos;

public class VeiculoNaoEncontrado extends Exception{
	
	public VeiculoNaoEncontrado() {
		super("Veiculo não encontrado");
	}
	
	public VeiculoNaoEncontrado(String msg) {
		super(msg);
	}
	
}
