package br.com.cepedi.model.pessoa;

import java.io.Serializable;

import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;
import br.com.cepedi.verificacoes.pessoa.VerificacoesPessoa;

public abstract class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static int numeroDePessoas = 0;
	protected int id;
	protected String nome;
	protected String CPF;
	
	
	public Pessoa(String nome, String CPF) throws NomePessoaInvalidoException, CPFPessoaInvalidoException {
		setNome(nome);
		setCPF(CPF);
		numeroDePessoas++;
		this.id = numeroDePessoas;
	}
	
	public Pessoa(int id , String nome, String CPF) throws NomePessoaInvalidoException, CPFPessoaInvalidoException {
		setNome(nome);
		setCPF(CPF);
		this.id = id;
	}
	
	public Pessoa() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws NomePessoaInvalidoException {
		if(!VerificacoesPessoa.verificaNome(nome)) {
			throw new NomePessoaInvalidoException();
		}
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) throws CPFPessoaInvalidoException {
		if(!VerificacoesPessoa.verificaCPF(cPF)) {
			throw new CPFPessoaInvalidoException();
		}
		this.CPF = cPF;			
	}
	
	
	

}
