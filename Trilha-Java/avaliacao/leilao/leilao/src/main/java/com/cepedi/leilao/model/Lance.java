package com.cepedi.leilao.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="LANCE")
public class Lance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Leilao leilao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Concorrente concorrente;

    @Column(name="VALOR")
    private BigDecimal valor;

    // Construtores, Getters e Setters

    public Lance() {}

    public Lance(Leilao leilao, Concorrente concorrente, BigDecimal valor) {
		this.id = null;
        this.leilao = leilao;
        this.concorrente = concorrente;
        this.valor = valor;
    }
    
    // Getters e Setters


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	public Concorrente getConcorrente() {
		return concorrente;
	}

	public void setConcorrente(Concorrente concorrente) {
		this.concorrente = concorrente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Lance [id=" + id + ", leilao=" + leilao + ", concorrente=" + concorrente + ", valor=" + valor + "]";
	}

	
    
    
}
