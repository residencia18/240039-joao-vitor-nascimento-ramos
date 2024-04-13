package br.com.cepedi.Voll.api.services.appointment.validations.register;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import br.com.cepedi.Voll.api.services.appointment.validations.register.ValidationAcheduleAppointment;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPatientExistence implements ValidationAcheduleAppointment {

    @Autowired
    private PatientRepository repositoryPatient;
    @Override
    public void validation(DataRegisterAppointment data) {
        if (!repositoryPatient.existsById(data.idPatient())) {
            throw new ValidationException("The provided patient id does not exist");
        }
    }
}
