package br.com.cepedi.exceptions.listaPontosDeParada;

public class PontoNaoEncontrado extends Exception{

	public PontoNaoEncontrado() {
		super("ponto não encontrado");
	}
	
	public PontoNaoEncontrado(String msg) {
		super(msg);
	}
}
