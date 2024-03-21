package com.biblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="AUTOR")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Autor {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Setter
	Long id;
	@Column(name="NAME" , nullable = false)
	private String nome;
	
	
    public Autor(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
    }
	

	public void setNome(String nome) {
		
	    if (nome == null || nome.trim().isEmpty()) {
	        throw new IllegalArgumentException("Nome é obrigatório");
	    }
		
		this.nome = nome;
	}



	

}
