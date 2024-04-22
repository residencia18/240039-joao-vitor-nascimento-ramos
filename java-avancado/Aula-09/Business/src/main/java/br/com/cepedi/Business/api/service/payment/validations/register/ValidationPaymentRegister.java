package br.com.cepedi.Business.api.service.payment.validations.register;

import br.com.cepedi.Business.api.model.records.payment.input.DataRegisterPayment;

public interface ValidationPaymentRegister {

    void validation(DataRegisterPayment data);

}
