package br.com.cepedi.Business.api.model.records.stock.input;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record DataRegisterStock(
        @Positive
        BigDecimal amount,
        @PositiveOrZero
        BigDecimal amountCritical

) {}
