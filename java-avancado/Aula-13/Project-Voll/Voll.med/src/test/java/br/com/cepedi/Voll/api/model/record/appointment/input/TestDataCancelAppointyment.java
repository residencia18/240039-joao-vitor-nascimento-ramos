package br.com.cepedi.Voll.api.model.record.appointment.input;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.enums.ReasonCancelAppointment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataCancelAppointyment {

    @Test
    @DisplayName("Test DataCancelAppointment Constructor")
    public void testDataCancelAppointmentConstructor() {
        DataCancelAppointment cancelAppointment = new DataCancelAppointment(1L, ReasonCancelAppointment.DOCTOR_CANCEL);

        assertNotNull(cancelAppointment);
        assertEquals(1L, cancelAppointment.idAppointment());
        assertEquals(ReasonCancelAppointment.DOCTOR_CANCEL, cancelAppointment.reason());
    }

}
