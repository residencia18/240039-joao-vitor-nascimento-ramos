package br.com.cepedi.exceptions.funcionario;

public class SalarioFuncionarioInvalidoException extends Exception{

	public SalarioFuncionarioInvalidoException() {
		super("Salário inválido. Deve ser maior do que R$ 0.0");
	}
	
	public SalarioFuncionarioInvalidoException(String msg) {
		super(msg);
	}

}
