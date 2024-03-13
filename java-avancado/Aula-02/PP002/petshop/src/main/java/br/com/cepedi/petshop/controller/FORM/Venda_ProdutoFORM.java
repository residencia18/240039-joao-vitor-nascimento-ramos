package br.com.cepedi.petshop.controller.FORM;

import java.math.BigInteger;

public class Venda_ProdutoFORM {
    
    private Long idProduto;
    private BigInteger quantidade;

    public Venda_ProdutoFORM() {
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
