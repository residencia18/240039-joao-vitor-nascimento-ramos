package br.com.cepedi.Voll.api.services.appointment.validations;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.repository.AppointmentRepository;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidationPatientWithoutOtherAppointmentOnTheDate  implements  ValidationAcheduleAppointment{

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validation(DataRegisterAppointment data) {
        LocalDateTime firstHour = data.date().withHour(7);
        LocalDateTime lastHour = data.date().withHour(18);
        Boolean otherAppointmnetOnTheDay = appointmentRepository.existsByPatientIdAndDataBetween(data.idPatient(), firstHour,  lastHour);
        if (otherAppointmnetOnTheDay) {
            throw new ValidationException("Patient already has an appointment scheduled for this day");
        }
    }

}
