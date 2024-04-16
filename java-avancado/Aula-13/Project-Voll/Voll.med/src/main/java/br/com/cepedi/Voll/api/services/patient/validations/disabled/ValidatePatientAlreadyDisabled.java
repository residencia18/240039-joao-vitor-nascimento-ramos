package br.com.cepedi.Voll.api.services.patient.validations.disabled;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientAlreadyDisabled implements ValidationDisabledPatient {

    @Autowired
    private PatientRepository repository;

    @Override
    public void validation(Long id) {
        Boolean patientActivated = repository.findActivatedById(id);
        if(!patientActivated){
            throw new ValidationException("The required patient already disabled");
        }
    }
}
