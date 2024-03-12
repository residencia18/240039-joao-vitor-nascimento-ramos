package br.com.cepedi.petshop.controller.FORM;

import java.math.BigInteger;

public class Venda_ProdutoFORM {
    
    private Long idCliente;
    private Long idProduto;
    private BigInteger quantidade;

    // Construtor vazio
    public Venda_ProdutoFORM() {
        // Construtor padrão vazio
    }

    // getters e setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public BigInteger getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigInteger quantidade) {
        this.quantidade = quantidade;
    }
}