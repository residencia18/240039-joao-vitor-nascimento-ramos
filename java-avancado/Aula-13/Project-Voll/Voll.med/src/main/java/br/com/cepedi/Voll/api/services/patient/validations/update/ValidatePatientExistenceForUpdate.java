package br.com.cepedi.Voll.api.services.patient.validations.update;

 import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;

@Component
public class ValidatePatientExistenceForUpdate implements ValidationUpdatePatient{

    @Autowired
    private PatientRepository repository;

    public void validation(DataUpdatePatient data){
        if(!repository.existsById(data.id())){
            throw new ValidationException("The required patient is does not exists");
        }
    }

}
