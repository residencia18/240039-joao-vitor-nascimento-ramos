package br.com.cepedi.Business.api.model.records.price.input;

import jakarta.validation.constraints.Positive;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

public record DataRegisterPrice (

        @Positive
        BigDecimal priceAcquisition,

        @Positive
        BigDecimal costPercentage,

        @Positive
        BigDecimal profitPercentageMin,

        @Positive
        BigDecimal profitPercentageMax
        ) {

}
