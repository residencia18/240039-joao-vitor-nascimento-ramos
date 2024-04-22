package br.com.cepedi.Business.api.model.records.saleProduct.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DataRegisterSaleProduct(

        @NotNull
        @Positive
        Long idProduct,

        @NotNull
        @Positive
        Long amount


) {

}
