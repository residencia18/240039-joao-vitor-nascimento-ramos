package br.com.cepedi.verificacoes.geral;

import java.math.BigDecimal;

public abstract class VerificacoesGeral {

	public static void verificaID(int id) {
		if(id<1) {
			throw new IllegalArgumentException("Não é permitido ids menores que 1");
		}
	}
	
	public static void verificaStringVaziaOuNula(String string) {
		if(string==null || string.isEmpty()) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}
	}
	
	public static void verificaLeituraNegativa(BigDecimal leitura) {
		if(leitura.compareTo(new BigDecimal("0.0")) < 0) {
			throw new IllegalArgumentException("valor da leitura não pode ser negativa");
		}
	}
	
	public static void verificaLeituraAtual(BigDecimal leituraAtual, BigDecimal ultimaLeitura) {
		if(leituraAtual.compareTo(ultimaLeitura) < 0 ) {
			throw new IllegalArgumentException("A leitura atual não pode ser menor que a ultima leitura");
		}
	}
	
}
