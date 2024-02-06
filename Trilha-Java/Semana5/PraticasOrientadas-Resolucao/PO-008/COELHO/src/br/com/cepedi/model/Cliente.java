package br.com.cepedi.model;

import java.util.Objects;

import br.com.cepedi.exceptions.cliente.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.cliente.NomePessoaInvalidoException;
import br.com.cepedi.verificacoes.cliente.VerificacoesCliente;

public class Cliente implements Comparable<Cliente>{

	public static int qntIdsGerados=0;
	int id;
	private String cpf;
	private String nome;
	
	
	public Cliente( String nome , String cpf) throws CPFPessoaInvalidoException, NomePessoaInvalidoException {
		super();
		setNome(nome);
		setCpf(cpf);
		qntIdsGerados++;
		this.id=qntIdsGerados;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) throws CPFPessoaInvalidoException {
		
		if(cpf==null || cpf.equals("")) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}
		
		
		if(!VerificacoesCliente.verificaCPF(cpf)) {
			throw new CPFPessoaInvalidoException();
		}
		
		
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws NomePessoaInvalidoException {
		
		if(nome==null || nome.equals("")) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}
		
		if(!VerificacoesCliente.verificaNome(nome)) {
			throw new NomePessoaInvalidoException();
		}
		
		this.nome = nome;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	
	@Override
	public int compareTo(Cliente o) {
		return Integer.compare(id, o.getId());
	}
	
	
	
}
