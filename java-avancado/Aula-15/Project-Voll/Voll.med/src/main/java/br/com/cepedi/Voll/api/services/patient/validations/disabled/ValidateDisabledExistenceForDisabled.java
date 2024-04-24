package br.com.cepedi.Voll.api.services.patient.validations.disabled;


import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDisabledExistenceForDisabled implements  ValidationDisabledPatient{
    @Autowired
    private PatientRepository repository;

    public void validation(Long id){
        if(!repository.existsById(id)){
            throw new ValidationException("The required patient is does not exists");
        }
    }
}
