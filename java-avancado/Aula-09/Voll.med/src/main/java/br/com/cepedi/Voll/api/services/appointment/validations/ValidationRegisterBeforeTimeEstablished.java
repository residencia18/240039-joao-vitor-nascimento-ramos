package br.com.cepedi.Voll.api.services.appointment.validations;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationRegisterBeforeTimeEstablished  implements  ValidationAcheduleAppointment{

    public void validation(DataRegisterAppointment data){
        LocalDateTime dateAppointmnet = data.date();
        var now = LocalDateTime.now();
        var minutesDifference = Duration.between(now, dateAppointmnet).toMinutes();

        if (minutesDifference < 30) {
            throw new ValidationException("Appointment must be scheduled at least 30 minutes in advance");
        }
    }
}
