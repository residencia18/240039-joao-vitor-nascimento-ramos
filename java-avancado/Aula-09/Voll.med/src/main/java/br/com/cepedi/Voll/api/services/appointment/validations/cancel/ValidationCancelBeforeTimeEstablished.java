package br.com.cepedi.Voll.api.services.appointment.validations.cancel;

import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.repository.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationCancelBeforeTimeEstablished implements ValidationCancelAppointment{

    @Autowired
    private AppointmentRepository repository;


    @Override
    public void validation(DataCancelAppointment data) {
        Appointment appointment = repository.getReferenceById(data.idAppointment());
        LocalDateTime now = LocalDateTime.now();
        Long diffHour = Duration.between(now,appointment.getDateService()).toHours();

        if(diffHour < 24){
            throw new ValidationException("The appointment can only be canceled at least 24 hours in advance");
        }
    }
}
