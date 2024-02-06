package br.com.cepedi.model.pessoa;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.cepedi.exceptions.funcionario.SalarioFuncionarioInvalidoException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;
import br.com.cepedi.verificacoes.pessoa.VerificacoesFuncionario;

public abstract class Funcionario extends Pessoa implements Serializable{

    protected BigDecimal salario;
	private static final long serialVersionUID = 4L;

    public Funcionario(String nome, String CPF, String salario) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, SalarioFuncionarioInvalidoException {
        super(nome, CPF);
        setSalario(salario);
    }
    
    public Funcionario(int id , String nome, String CPF, String salario) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, SalarioFuncionarioInvalidoException {
        super(id ,nome, CPF);
        setSalario(salario);
    }
    
    public Funcionario(int id , String nome, String CPF, BigDecimal salario) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, SalarioFuncionarioInvalidoException {
        super(id ,nome, CPF);
        setSalario(salario);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(String salario) throws SalarioFuncionarioInvalidoException {
    	
    	if(salario==null) {
            throw new SalarioFuncionarioInvalidoException();
    	}
    	
        try {
            BigDecimal salarioDecimal = new BigDecimal(salario);
            if (!VerificacoesFuncionario.VerificaSalario(salarioDecimal)) {
                throw new SalarioFuncionarioInvalidoException();
            }
            this.salario = salarioDecimal;
        } catch (NumberFormatException e) {
            throw new SalarioFuncionarioInvalidoException("Formato inválido para o salário");
        }
    }
    
    public void setSalario(BigDecimal salario) throws SalarioFuncionarioInvalidoException {
    	
    	if(salario==null) {
            throw new SalarioFuncionarioInvalidoException();
    	}
    	
        try {
            if (!VerificacoesFuncionario.VerificaSalario(salario)) {
                throw new SalarioFuncionarioInvalidoException();
            }
            this.salario = salario;
        } catch (NumberFormatException e) {
            throw new SalarioFuncionarioInvalidoException("Formato inválido para o salário");
        }
    }

}
