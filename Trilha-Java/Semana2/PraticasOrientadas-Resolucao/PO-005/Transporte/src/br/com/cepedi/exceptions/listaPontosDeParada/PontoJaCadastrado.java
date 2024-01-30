package br.com.cepedi.exceptions.listaPontosDeParada;

public class PontoJaCadastrado extends Exception{
	
	public PontoJaCadastrado() {
		super("Esse ponto já foi cadastrado.");
	}
	
	public PontoJaCadastrado(String msg) {
		super(msg);
	}

}
