package br.com.cepedi.atividade2.model;

import br.com.cepedi.atividade2.Exceptions.CodigoInvalidoException;
import br.com.cepedi.atividade2.validacoes.ValidacoesProduto;

public class Produto {
	
	private String codigo;
	private String nome;
	private double preco;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Produto(String codigo , String nome, double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
		try {
			if(ValidacoesProduto.validarCodigo(codigo)) {
				this.codigo = codigo;
			}
		}catch(CodigoInvalidoException e) {
            System.err.println("Erro: " + e.getMessage());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", preco=" + preco + "]";
	}


	
	

}
