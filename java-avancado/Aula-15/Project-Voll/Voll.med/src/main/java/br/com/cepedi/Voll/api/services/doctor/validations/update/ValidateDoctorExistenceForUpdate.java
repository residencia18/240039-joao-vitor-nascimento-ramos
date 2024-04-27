package br.com.cepedi.Voll.api.services.doctor.validations.update;

import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorExistenceForUpdate implements ValidationUpdateDoctor {

    @Autowired
    private DoctorRepository repository;

    public void validation(Long id ,DataUpdateDoctor data){
        if(!repository.existsById(id)){
            throw new ValidationException("The required doctor is does not exists");
        }
    }

}
