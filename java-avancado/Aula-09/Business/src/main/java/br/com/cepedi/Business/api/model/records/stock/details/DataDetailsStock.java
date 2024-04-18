package br.com.cepedi.Business.api.model.records.stock.details;

import br.com.cepedi.Business.api.model.entitys.Stock;

import java.math.BigDecimal;

public record DataDetailsStock(
        BigDecimal amount,
        BigDecimal amountCritical
) {
    public DataDetailsStock(Stock stock) {
        this(stock.getAmount(), stock.getAmountCritical());
    }
}