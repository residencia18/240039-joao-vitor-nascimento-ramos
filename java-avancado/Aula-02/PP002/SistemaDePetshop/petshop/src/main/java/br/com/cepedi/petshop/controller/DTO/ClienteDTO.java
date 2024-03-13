package br.com.cepedi.petshop.controller.DTO;

import br.com.cepedi.petshop.model.Cliente;

public class ClienteDTO {
	
	private String nome;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public ClienteDTO(Cliente cliente) {
		this.nome = cliente.getNome();
	}

}
