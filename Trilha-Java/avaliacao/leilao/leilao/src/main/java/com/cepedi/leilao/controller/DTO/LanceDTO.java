package com.cepedi.leilao.controller.DTO;

import com.cepedi.leilao.model.Lance;

import java.math.BigDecimal;

public class LanceDTO {

    private BigDecimal valor;

    public LanceDTO(Lance lance) {
        this.valor = lance.getValor();
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "LanceDTO{" +
                "valor=" + valor +
                '}';
    }
}
