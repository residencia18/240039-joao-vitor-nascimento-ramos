package br.com.cepedi.Business.api.service.payment.validations.update;

import br.com.cepedi.Business.api.model.records.employee.input.DataUpdateEmployee;

public interface ValidationPaymentUpdate {

    void validation(DataUpdateEmployee data);

}
