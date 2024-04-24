package br.com.cepedi.Voll.api.services.appointment.validations.register;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.services.appointment.validations.register.ValidationAcheduleAppointment;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidationTimeOperationClinic implements ValidationAcheduleAppointment {

    public void validation(DataRegisterAppointment data){
        LocalDateTime dateAppointmnet = data.date();
        var sunday = dateAppointmnet.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpenClinic = dateAppointmnet.getHour() < 7;
        var afterClosedClinic = dateAppointmnet.getHour() > 18;
        if (sunday || beforeOpenClinic || afterClosedClinic) {
            throw new ValidationException("Appointment outside of the clinic's operating hours.");
        }
    }

}
