package br.com.cepedi.atividade2.validacoes;

import br.com.cepedi.atividade2.Exceptions.CodigoInvalidoException;

public abstract class ValidacoesProduto {

	public static boolean validarCodigo(String codigo) throws CodigoInvalidoException {
		if(!codigo.matches("\\d+")) {
			throw new CodigoInvalidoException("Codigo inválido. Codigo deve conter apenas números.");
		}
        return true;
	}
	
}
