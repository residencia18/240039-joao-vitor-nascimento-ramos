package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private DadosPessoais dadosPessoais;
	
	
	

	public Cliente(String nome, String cpf) {
		super();
		this.dadosPessoais = new DadosPessoais(nome,cpf);

	}




	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	
	public String getNome() {
		return this.dadosPessoais.getNome();
	}
	
	public String getCPF() {
		return this.dadosPessoais.getCpf();
	}


	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}





}
