package br.com.cepedi.petshop.model;

import java.math.BigInteger;

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
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="ID_VENDA", nullable = false) 
    private Venda venda; 
    
    @ManyToOne
    @JoinColumn(name="ID_PRODUTO", nullable = false)
    private Produto produto;
    
    @Column(name="QUANTIDADE")
    private BigInteger quantidade;

    public Venda_Produto() {
    }

    public Venda_Produto(Venda venda, Produto produto, BigInteger quantidade) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
