package br.com.cepedi.Business.api.service.supplier.validations.update;

import br.com.cepedi.Business.api.model.records.supplier.input.DataUpdateSupplier;

public interface ValidateUpdateSupplier {

    void validation(Long id , DataUpdateSupplier data);

}
