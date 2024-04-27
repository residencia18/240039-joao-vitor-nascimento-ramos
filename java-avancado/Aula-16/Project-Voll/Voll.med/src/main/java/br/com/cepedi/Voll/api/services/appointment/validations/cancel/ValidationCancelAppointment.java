package br.com.cepedi.Voll.api.services.appointment.validations.cancel;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;

public interface ValidationCancelAppointment {

    void validation(DataCancelAppointment data);

}
