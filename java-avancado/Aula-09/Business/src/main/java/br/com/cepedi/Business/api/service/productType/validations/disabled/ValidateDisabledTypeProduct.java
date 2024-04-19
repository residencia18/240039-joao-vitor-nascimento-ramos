package br.com.cepedi.Business.api.service.productType.validations.disabled;

import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataUpdateProductType;

public interface ValidateDisabledTypeProduct {


    void validation(Long id);
}
