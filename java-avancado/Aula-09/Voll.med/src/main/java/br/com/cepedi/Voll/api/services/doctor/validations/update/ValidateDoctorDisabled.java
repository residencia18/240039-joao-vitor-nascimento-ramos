package br.com.cepedi.Voll.api.services.doctor.validations.update;

import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorDisabled implements  ValidationUpdateDoctor{

    @Autowired
    private DoctorRepository repository;
    @Override
    public void validation(DataUpdateDoctor data) {
        Boolean doctorActivated = repository.findActivatedById(data.id());
        if(!doctorActivated){
            throw new ValidationException("The required doctor is disabled");
        }
    }
}
