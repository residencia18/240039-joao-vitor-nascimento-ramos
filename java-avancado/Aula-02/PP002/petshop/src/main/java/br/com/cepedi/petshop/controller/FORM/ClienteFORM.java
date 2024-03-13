package br.com.cepedi.petshop.controller.FORM;


import br.com.cepedi.petshop.exceptions.CPFInvalidoException;
import br.com.cepedi.petshop.exceptions.NomeInvalidoException;
import br.com.cepedi.petshop.model.Cliente;
import br.com.cepedi.petshop.verificacoes.Verificacoes;

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