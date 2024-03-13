package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.Exceptions.CPFInválidoException;
import com.biblioteca.biblioteca.model.Cliente;
import com.biblioteca.biblioteca.utils.VerificacoesCliente;

public class ClienteFORM {
	
	private String nome;
	private String cpf;
	
	
	public ClienteFORM(String nome, String cpf) throws CPFInválidoException {
		super();
		
		if(!VerificacoesCliente.validarCPF(cpf)) {
			throw new CPFInválidoException();
		}
		
		this.nome = nome;
		this.cpf = cpf.replaceAll("[^0-9]", "");
;
	}


	public ClienteFORM() {
		super();
	}

	
	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		return cliente;
	}
	

}
