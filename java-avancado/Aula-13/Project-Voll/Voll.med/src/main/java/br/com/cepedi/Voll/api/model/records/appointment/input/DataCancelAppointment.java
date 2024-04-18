package br.com.cepedi.Voll.api.model.records.appointment.input;

import br.com.cepedi.Voll.api.model.records.appointment.enums.ReasonCancelAppointment;
import jakarta.validation.constraints.NotNull;

public record DataCancelAppointment(
        @NotNull
        Long idAppointment,

        @NotNull
        ReasonCancelAppointment reason

){

}

