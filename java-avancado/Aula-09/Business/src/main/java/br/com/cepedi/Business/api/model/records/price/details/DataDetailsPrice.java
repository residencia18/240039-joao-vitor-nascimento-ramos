package br.com.cepedi.Business.api.model.records.price.details;

import br.com.cepedi.Business.api.model.embeddables.product.Price;

import java.math.BigDecimal;


public record DataDetailsPrice(
        BigDecimal priceAcquisition,
        BigDecimal costPercentage,
        BigDecimal priceCost,
        BigDecimal profitPercentageMin,
        BigDecimal salePriceMin,
        BigDecimal profitPercentageMax,
        BigDecimal salePriceMax
) {
    public DataDetailsPrice(Price price) {
        this(
                price.getPriceAcquisition(),
                price.getCostPercentage(),
                price.getPriceCost(),
                price.getProfitPercentageMin(),
                price.getSalePriceMin(),
                price.getProfitPercentageMax(),
                price.getSalePriceMax()
        );
    }
}
