package br.com.cepedi.Business.api.service.product.validations.update;

import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;

public interface ValidateProductUpdate {


    void validation(Long id , DataUpdateProduct data);
}
