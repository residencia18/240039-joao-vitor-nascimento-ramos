package com.biblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="EDITORA")
@Getter
@NoArgsConstructor
@ToString
public class Editora {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Setter
	private Long id;
	@Column(name="NOME" , nullable = false)
	private String nome;
	
	
	
	public Editora(Editora editora) {
		this.nome = editora.getNome();
		this.id = editora.getId();
	}


	public void setNome(String nome) {
		
	    if (nome == null || nome.trim().isEmpty()) {
	        throw new IllegalArgumentException("Nome é obrigatório");
	    }
		
		this.nome = nome;
	}


	
	
	
	

}
