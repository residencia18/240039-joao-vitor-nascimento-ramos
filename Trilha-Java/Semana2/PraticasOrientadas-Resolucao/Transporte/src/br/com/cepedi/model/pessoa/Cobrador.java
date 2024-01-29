package br.com.cepedi.model.pessoa;

import br.com.cepedi.exceptions.funcionario.SalarioFuncionarioInvalidoException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;

public class Cobrador extends Funcionario{

	public Cobrador(String nome, String CPF, String salario) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, SalarioFuncionarioInvalidoException {
		super(nome, CPF, salario);
	}

	@Override
	public String toString() {
		return "[COBRADOR - ID= " + id + "\nNome= " + nome + "\nCPF= " + CPF + "\nSalário= " + salario.toString() +"]";
	}
	
	
	
	

}
