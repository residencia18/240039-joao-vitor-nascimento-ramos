package br.com.cepedi.Voll.api.services.patient.validations.update;

import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientDisabledForUpdate implements  ValidationUpdatePatient {

    @Autowired
    private PatientRepository repository;

    @Override
    public void validation(Long id, DataUpdatePatient data) {
        Boolean patientActivated = repository.findActivatedById(id);
        if(!patientActivated){
            throw new ValidationException("The required patient is disabled");
        }
    }
}
