package br.com.cepedi.Business.api.model.records.product.details;

import br.com.cepedi.Business.api.model.entitys.*;
import br.com.cepedi.Business.api.model.records.price.details.DataDetailsPrice;
import br.com.cepedi.Business.api.model.records.productType.details.DataDetailsProductType;
import br.com.cepedi.Business.api.model.records.stock.details.DataDetailsStock;
import br.com.cepedi.Business.api.model.records.supplier.details.DataDetailsSupplier;


public record DataDetailsProduct(

        Long id,
        String code,

        String name,

        String description,

        String color,

        DataDetailsPrice price,

        DataDetailsStock stock,
        DataDetailsSupplier supplier,

        DataDetailsProductType productType
) {
    public DataDetailsProduct(Product product) {
        this(product.getId(), product.getCode(), product.getName(), product.getDescription(),
                product.getColor(), new DataDetailsPrice(product.getPrice()), new DataDetailsStock(product.getStock()),
                new DataDetailsSupplier(product.getSupplier()), new DataDetailsProductType(product.getProductType()));
    }
}

