package br.com.cepedi.Library.api.service.loan.validations.update;

import br.com.cepedi.Library.api.model.records.loan.input.DataUpdateLoan;

public interface ValidationUpdateLoan {

    void validate(DataUpdateLoan data);
}
