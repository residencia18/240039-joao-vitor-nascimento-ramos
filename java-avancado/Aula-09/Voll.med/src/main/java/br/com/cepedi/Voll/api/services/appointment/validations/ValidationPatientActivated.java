package br.com.cepedi.Voll.api.services.appointment.validations;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPatientActivated implements  ValidationAcheduleAppointment {
    @Autowired
    private PatientRepository repositoryPatient;

    public void validation(DataRegisterAppointment data){

        Boolean patientActivated = repositoryPatient.findActivatedById(data.idPatient());
        if(patientActivated){
            throw new ValidationException("Appointment cannot be scheduled with an inactive patient");
        }

    }
}
