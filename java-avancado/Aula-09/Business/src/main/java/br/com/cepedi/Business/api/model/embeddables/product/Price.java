package br.com.cepedi.Business.api.model.embeddables.product;

import br.com.cepedi.Business.api.model.records.price.input.DataRegisterPrice;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Price {


    private BigDecimal priceAcquisition;

    private BigDecimal costPercentage;

    private BigDecimal priceCost;

    private BigDecimal profitPercentageMin;

    private BigDecimal salePriceMin;

    private BigDecimal profitPercentageMax;

    private BigDecimal salePriceMax;


    public Price(DataRegisterPrice data) {
        this.priceAcquisition = data.priceAcquisition();
        this.costPercentage = data.costPercentage();
        this.priceCost = calculatePriceCost(this.priceAcquisition, this.costPercentage);
        this.profitPercentageMin = data.profitPercentageMin();
        this.profitPercentageMax = data.profitPercentageMax();
        this.salePriceMax = calculateSalePrice(this.priceCost, this.profitPercentageMax);
        this.salePriceMin = calculateSalePrice(this.priceCost, this.profitPercentageMin);
    }

    private BigDecimal calculatePriceCost(BigDecimal priceAcquisition, BigDecimal costPercentage) {
        return priceAcquisition.multiply((costPercentage.divide(new BigDecimal(100))).add(BigDecimal.ONE));
    }

    private BigDecimal calculateSalePrice(BigDecimal priceCost, BigDecimal profitPercentage) {
        return priceCost.multiply((profitPercentage.divide(new BigDecimal(100))).add(BigDecimal.ONE));
    }
}