package br.com.cepedi.exceptions.imovel;

public class ImovelJaExistente extends Exception {

	public ImovelJaExistente() {
		super("Imovel já existente");
	}
	
}
