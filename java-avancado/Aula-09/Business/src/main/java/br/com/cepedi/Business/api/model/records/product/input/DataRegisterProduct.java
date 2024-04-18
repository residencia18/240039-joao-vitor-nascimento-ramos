package br.com.cepedi.Business.api.model.records.product.input;

import br.com.cepedi.Business.api.model.records.price.details.DataDetailsPrice;
import br.com.cepedi.Business.api.model.records.price.input.DataRegisterPrice;
import br.com.cepedi.Business.api.model.records.stock.input.DataRegisterStock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterProduct(

        String code,

        @NotBlank
        String name,

        String description,

        String color,

        @NotNull
        DataRegisterPrice price,

        @NotNull
        DataRegisterStock stock,

        @NotNull
        Long idSupplier,

        @NotNull
        Long idProductType

) {
}
