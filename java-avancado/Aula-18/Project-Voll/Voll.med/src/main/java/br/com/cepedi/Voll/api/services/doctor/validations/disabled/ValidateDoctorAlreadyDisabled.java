package br.com.cepedi.Voll.api.services.doctor.validations.disabled;

import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorAlreadyDisabled implements  ValidationDisabledDoctor{

    @Autowired
    private DoctorRepository repository;


    @Override
    public void validation(Long id) {
        Boolean doctorActivated = repository.findActivatedById(id);
        if(!doctorActivated){
            throw new ValidationException("The required doctor already disabled");
        }
    }
}
