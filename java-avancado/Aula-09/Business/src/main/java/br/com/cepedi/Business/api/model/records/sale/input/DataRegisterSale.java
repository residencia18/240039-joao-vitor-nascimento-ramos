package br.com.cepedi.Business.api.model.records.sale.input;

import br.com.cepedi.Business.api.model.records.saleProduct.input.DataRegisterSaleProduct;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public record DataRegisterSale(

        @NotNull
        @Positive
        @JsonAlias("idClient")
        Long idClient,

        @NotNull
        @Positive
        @JsonAlias("idEmployee")
        Long idEmployee,

        @NotNull
        @JsonAlias("products")
        List<DataRegisterSaleProduct> products,


        @JsonAlias("discount")
        BigDecimal discount

) {
}
