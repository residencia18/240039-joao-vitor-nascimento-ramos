package br.com.cepedi.petshop.model;

import java.math.BigDecimal;

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
@Table(name="TIPO_PRODUTO")
@Getter
@NoArgsConstructor
@ToString
public class TipoProduto {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Setter
	Long id;
	@Column(name="NAME")
	private String nome;
	
	
	public TipoProduto(String nome) {
		super();
		this.nome = nome;
	}

	

	public void setNome(String nome) {
		
	    if (nome == null || nome.trim().isEmpty()) {
	        throw new IllegalArgumentException("Nome é obrigatório");
	    }
		
		this.nome = nome;
	}
	
	
	
	

}
