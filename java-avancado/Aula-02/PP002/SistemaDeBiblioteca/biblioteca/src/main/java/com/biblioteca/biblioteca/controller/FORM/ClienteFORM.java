package com.biblioteca.biblioteca.controller.FORM;


import com.biblioteca.biblioteca.Exceptions.CPFInvalidoException;
import com.biblioteca.biblioteca.Exceptions.NomeInvalidoException;
import com.biblioteca.biblioteca.model.Cliente;

public class ClienteFORM {
	
	private String nome;
	private String cpf;
	

    public ClienteFORM(String nome, String cpf){

        
        this.nome = nome;
        this.cpf = cpf;
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


	public void setCpf(String cpf)  {
		
		this.cpf = cpf;
	}
	
	
	public Cliente toCliente() throws NomeInvalidoException, CPFInvalidoException {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		return cliente;
	}
	

}
