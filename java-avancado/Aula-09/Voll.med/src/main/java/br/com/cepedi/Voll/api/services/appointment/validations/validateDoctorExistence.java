package br.com.cepedi.Voll.api.services.appointment.validations;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validateDoctorExistence implements ValidationAcheduleAppointment{

    @Autowired
    private DoctorRepository repositoryDoctor;

    @Override
    public void validation(DataRegisterAppointment data) {
        if (data.idDoctor() != null && !repositoryDoctor.existsById(data.idDoctor())) {
            throw new ValidationException("The provided doctor id does not exist");
        }
    }
}
