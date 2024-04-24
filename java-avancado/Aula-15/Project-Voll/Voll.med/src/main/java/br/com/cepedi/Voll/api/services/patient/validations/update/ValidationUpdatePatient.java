package br.com.cepedi.Voll.api.services.patient.validations.update;

import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;

public interface ValidationUpdatePatient {

    void validation(DataUpdatePatient data);
}
