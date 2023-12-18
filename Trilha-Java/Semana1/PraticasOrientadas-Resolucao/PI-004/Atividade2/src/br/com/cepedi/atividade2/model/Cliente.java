package br.com.cepedi.atividade2.model;

import br.com.cepedi.atividade2.Exceptions.CpfInvalidoException;
import br.com.cepedi.atividade2.validacoes.ValidacoesCliente;

public class Cliente {

	private String nome;
	private String cpf;
	
	public Cliente(String nome, String cPF) {
		super();
		this.nome = nome;
		try {
			if(ValidacoesCliente.validarCpf(cPF)) {
				this.cpf = cPF;
			}
		}catch(CpfInvalidoException e) {
            System.err.println("Erro: " + e.getMessage());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cPF) {
		cpf = cPF;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + "]";
	}
	
	
	
	
	
}
