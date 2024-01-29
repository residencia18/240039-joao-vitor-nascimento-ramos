package br.com.cepedi.model.pessoa;

import br.com.cepedi.exceptions.funcionario.SalarioFuncionarioInvalidoException;
import br.com.cepedi.exceptions.motorista.CNHMotoristaInvalidaException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;
import br.com.cepedi.verificacoes.pessoa.VerificacoesMotorista;

public class Motorista extends Funcionario{
	
	private String CNH;

	public Motorista(String nome, String CPF, String salario, String CNH) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, SalarioFuncionarioInvalidoException, CNHMotoristaInvalidaException {
		super(nome, CPF, salario);
		setCNH(CNH);
	}

	public String getCNH() {
		return CNH;
	}

	public void setCNH(String CNH) throws CNHMotoristaInvalidaException {
		if(CNH==null || !VerificacoesMotorista.verificaCNH(CNH)) {
			throw new CNHMotoristaInvalidaException();
		}
		this.CNH = CNH;
	}

	@Override
	public String toString() {
		return "[MOTORISTA - ID= " + id + "\nNome= " + nome + "\nCPF= " + CPF + "\nSalário= " + salario.toString() + "\nCNH=" + CNH + "]";
	}
	
	

}
