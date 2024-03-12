package br.com.cepedi.petshop.model;

import java.math.BigInteger;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="VENDA_PRODUTO")
public class Venda_Produto {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_CLIENTE" , nullable = false)
	private Cliente cliente;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_PRODUTO" , nullable = false)
	private Produto produto;
	
	@Column(name="QUANTIDADE")
	private BigInteger quantidade;

	public Venda_Produto(Cliente cliente, Produto produto, BigInteger quantidade) {
		super();
		this.cliente = cliente;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
	
	
}
