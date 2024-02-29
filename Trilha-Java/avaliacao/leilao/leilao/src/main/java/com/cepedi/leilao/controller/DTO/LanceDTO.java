package com.cepedi.leilao.controller.DTO;

import com.cepedi.leilao.model.Lance;

import java.math.BigDecimal;

public class LanceDTO {

    private Long leilaoId;
    private Long concorrenteId;
    private BigDecimal valor;

    public LanceDTO(Lance lance) {
        this.leilaoId = lance.getLeilao().getId();
        this.concorrenteId = lance.getConcorrente().getId();
        this.valor = lance.getValor();
    }

    public Long getLeilaoId() {
        return leilaoId;
    }

    public Long getConcorrenteId() {
        return concorrenteId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "LanceDTO{" +
                "leilaoId=" + leilaoId +
                ", concorrenteId=" + concorrenteId +
                ", valor=" + valor +
                '}';
    }
}
