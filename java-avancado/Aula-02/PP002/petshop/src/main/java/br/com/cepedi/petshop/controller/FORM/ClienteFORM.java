package br.com.cepedi.petshop.controller.FORM;


import br.com.cepedi.petshop.exceptions.CPFInvalidoException;
import br.com.cepedi.petshop.model.Cliente;
import br.com.cepedi.petshop.verificacoes.Verificacoes;

public class ClienteFORM {
	
	private String nome;
	private String cpf;
	
	
	public ClienteFORM(String nome, String cpf) {
		super();
		

		
		this.nome = nome;
		this.cpf = cpf;
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


	public void setCpf(String cpf) throws CPFInvalidoException {
		
		if(!Verificacoes.validarCPF(cpf)) {
			throw new CPFInvalidoException();
		}
		this.cpf = cpf.replaceAll("[^0-9]", "");
	}
	
	
	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		return cliente;
	}
	

}