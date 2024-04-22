package br.com.cepedi.Business.api.model.records.saleProduct.details;

import br.com.cepedi.Business.api.model.entitys.Product;
import br.com.cepedi.Business.api.model.entitys.SaleProduct;
import br.com.cepedi.Business.api.model.records.product.details.DataDetailsProduct;

public record DataDetailsSaleProduct(

    Long id,

    DataDetailsProduct product,

    Long amount

) {

    public DataDetailsSaleProduct(SaleProduct saleProduct){
        this(saleProduct.getId(), new DataDetailsProduct(saleProduct.getProduct()) , saleProduct.getAmount());
    }

}
