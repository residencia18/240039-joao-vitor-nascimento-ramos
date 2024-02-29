package com.cepedi.leilao.controller.form;

import java.math.BigDecimal;

public class LanceFORM {
    
    private Long leilaoId;
    private Long concorrenteId;
    private BigDecimal valor;

    // Construtores, Getters e Setters
    public LanceFORM() {}

    public LanceFORM(Long leilaoId, Long concorrenteId, BigDecimal valor) {
        this.leilaoId = leilaoId;
        this.concorrenteId = concorrenteId;
        this.valor = valor;
    }

    public Long getLeilaoId() {
        return leilaoId;
    }

    public void setLeilaoId(Long leilaoId) {
        this.leilaoId = leilaoId;
    }

    public Long getConcorrenteId() {
        return concorrenteId;
    }

    public void setConcorrenteId(Long concorrenteId) {
        this.concorrenteId = concorrenteId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "LanceFORM{" +
                "leilaoId=" + leilaoId +
                ", concorrenteId=" + concorrenteId +
                ", valor=" + valor +
                '}';
    }
}

