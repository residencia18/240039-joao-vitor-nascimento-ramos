package com.cepedi.leilao.model;

import java.math.BigDecimal;
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
@Table(name="LEILAO")
public class Leilao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="DESCRICAO")
    private String descricao;
    @Column(name="VALOR_MINIMO")
    private BigDecimal valorMinimo;
    @Column(name="STATUS")
    private Boolean status;
    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Lance> lances;
    
  
    
    // Construtores, Getters e Setters

    
    
    public Leilao(String descricao, BigDecimal valorMinimo, Boolean status, List<Lance> lances) {
		super();
		this.id = null;
		this.descricao = descricao;
		this.valorMinimo = valorMinimo;
		this.status = status;
		this.lances = lances;
	}
    
    
	public Leilao() {
		super();
	}


	// Getters e Setters
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}


	public List<Lance> getLances() {
		return lances;
	}


	public void setLances(List<Lance> lances) {
		this.lances = lances;
	} 
    
    
	

}
