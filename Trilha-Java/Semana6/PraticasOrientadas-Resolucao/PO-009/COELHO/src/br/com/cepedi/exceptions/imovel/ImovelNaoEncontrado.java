package br.com.cepedi.exceptions.imovel;

public class ImovelNaoEncontrado extends Exception{
	public ImovelNaoEncontrado() {
		super("Imovel não encontrado");
	}
}
