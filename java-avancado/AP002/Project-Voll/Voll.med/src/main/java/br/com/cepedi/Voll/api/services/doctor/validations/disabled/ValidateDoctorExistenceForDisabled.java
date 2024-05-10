package br.com.cepedi.Voll.api.services.doctor.validations.disabled;

import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorExistenceForDisabled implements ValidationDisabledDoctor {


    @Autowired
    private DoctorRepository repository;

    public void validation(Long id){
        if(!repository.existsById(id)){
            throw new ValidationException("The required doctor is does not exists");
        }
    }
}
