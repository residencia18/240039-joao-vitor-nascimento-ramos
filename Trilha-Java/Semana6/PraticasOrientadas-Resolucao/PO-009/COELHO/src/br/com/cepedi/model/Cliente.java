package br.com.cepedi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.cepedi.exceptions.cliente.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.cliente.NomePessoaInvalidoException;
import br.com.cepedi.verificacoes.cliente.VerificacoesCliente;
import br.com.cepedi.verificacoes.geral.VerificacoesGeral;

public class Cliente implements Comparable<Cliente>{

	//-----ATRIBUTOS
	private int id;
	private String cpf;
	private String nome;
	List<Imovel> imoveis;
	
	//-----CONSTRUTORES

	
    public Cliente(String nome, String cpf) throws CPFPessoaInvalidoException, NomePessoaInvalidoException {
        super();
        setNome(nome);
        setCpf(cpf);
        imoveis = new ArrayList<Imovel>();
    }
	
	public Cliente( int id, String nome , String cpf) throws CPFPessoaInvalidoException, NomePessoaInvalidoException {
		super();
		setNome(nome);
		setCpf(cpf);
		setId(id);
	}
	
	public Cliente() {
		
	}


	
	//-----GETTERS AND SETTERS
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		VerificacoesGeral.verificaID(id);	
		this.id = id;	
	}
	
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) throws CPFPessoaInvalidoException, NomePessoaInvalidoException {
		
		VerificacoesGeral.verificaStringVaziaOuNula(cpf);
		
		VerificacoesCliente.verificaCPF(cpf);
		
		
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws NomePessoaInvalidoException, CPFPessoaInvalidoException {
		
		VerificacoesGeral.verificaStringVaziaOuNula(nome);

		
		VerificacoesCliente.verificaNome(nome);
		
		this.nome = nome;
	}
	
	
	//-----TO STRING

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + "]";
	}

	//-----EQUALS AND HASHCODE
	
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
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

	
	//-----COMPARE_TO
	
	@Override
	public int compareTo(Cliente o) {
		return Integer.compare(id, o.getId());
	}
	
	//----------------- LISTA DE IMOVEIS
	
	public void adicionaImovelNaLista(Imovel i) {
		this.imoveis.add(i);
	}
	
	public void removeImovelNaLista(Imovel i) {
		this.imoveis.remove(i);
	}
	
	
	
	
}
