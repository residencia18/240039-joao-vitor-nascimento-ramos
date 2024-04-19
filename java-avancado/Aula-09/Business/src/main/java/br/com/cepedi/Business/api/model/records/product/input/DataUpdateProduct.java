package br.com.cepedi.Business.api.model.records.product.input;

import br.com.cepedi.Business.api.model.records.price.input.DataRegisterPrice;
import br.com.cepedi.Business.api.model.records.stock.input.DataRegisterStock;
import jakarta.validation.Valid;


public record DataUpdateProduct(

        String code,

        String name,

        String description,

        String color,

        @Valid
        DataRegisterPrice price,

        @Valid
        DataRegisterStock stock,
        Long idSupplier,

        Long idProductType


) {
}
