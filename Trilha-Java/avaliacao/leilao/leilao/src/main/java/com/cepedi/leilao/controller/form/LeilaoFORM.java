package com.cepedi.leilao.controller.form;

import java.math.BigDecimal;

import com.cepedi.leilao.model.Leilao;

public class LeilaoFORM {
    
    private String descricao;
    private BigDecimal valorMinimo;
    private Boolean status;

    // Construtores, Getters e Setters
    public LeilaoFORM() {}

    public LeilaoFORM(String descricao, BigDecimal valorMinimo, Boolean status) {
        this.descricao = descricao;
        this.valorMinimo = valorMinimo;
        this.status = status;
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

    @Override
    public String toString() {
        return "LeilaoForm{" +
                "descricao='" + descricao + '\'' +
                ", valorMinimo=" + valorMinimo +
                ", status=" + status +
                '}';
    }
    
    public Leilao toLeilao() {
    	Leilao leilao = new Leilao();
    	leilao.setDescricao(descricao);
    	leilao.setStatus(status);
    	leilao.setValorMinimo(valorMinimo);
        return leilao;
    }
}
