package br.com.cepedi.model.pessoa;

import java.io.Serializable;

import br.com.cepedi.exceptions.funcionario.SalarioFuncionarioInvalidoException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;

public class Cobrador extends Funcionario implements Serializable{

	
	private static final long serialVersionUID = 5L;

	public Cobrador(String nome, String CPF, String salario) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, SalarioFuncionarioInvalidoException {
		super(nome, CPF, salario);
	}

	@Override
	public String toString() {
		return "[COBRADOR - ID= " + id + " Nome= " + nome + " CPF= " + CPF + " Salário= " + salario.toString() +"]";
	}
	
	
	
	

}
