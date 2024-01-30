package br.com.cepedi.verificacoes.pessoa;

import java.math.BigDecimal;

public class VerificacoesFuncionario {
	
	public static boolean VerificaSalario(BigDecimal salario) {
		return salario.compareTo(new BigDecimal(0.0)) < 0 ? false : true; 
	}
	
}
