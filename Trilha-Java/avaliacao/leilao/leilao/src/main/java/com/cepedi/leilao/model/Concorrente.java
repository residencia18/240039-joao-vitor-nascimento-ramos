package com.cepedi.leilao.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="CONCORRENTE")
public class Concorrente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="NOME")
	private String nome;
	@Column(name="CPF" ,  unique = true)
	private String cpf;
    @OneToMany(mappedBy = "concorrente", cascade = CascadeType.ALL)
    private List<Lance> lances;

	
	public Concorrente() {
		
	}
	
	public Concorrente(String nome, String cpf) {
		super();
		this.id = null;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Concorrente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
	
	
}
