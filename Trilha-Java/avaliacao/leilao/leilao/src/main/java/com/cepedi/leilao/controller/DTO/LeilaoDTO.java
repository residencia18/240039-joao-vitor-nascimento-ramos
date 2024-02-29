package com.cepedi.leilao.controller.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.cepedi.leilao.model.Leilao;

public class LeilaoDTO {
    
    private String descricao;
    private BigDecimal valorMinimo;
    private Boolean status;
    private List<LanceDTO> lances;

    public LeilaoDTO(Leilao leilao) {
        this.descricao = leilao.getDescricao();
        this.valorMinimo = leilao.getValorMinimo();
        this.status = leilao.getStatus();
        this.lances = leilao.getLances().stream().map(LanceDTO::new).collect(Collectors.toList());
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public Boolean getStatus() {
        return status;
    }

    public List<LanceDTO> getLances() {
        return lances;
    }

    @Override
    public String toString() {
        return "LeilaoDTO{" + "descricao=" + descricao + ", valorMinimo=" + valorMinimo + ", status=" + status + ", lances=" + lances + '}';
    }
}
