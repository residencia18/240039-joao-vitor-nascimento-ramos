package br.com.cepedi.Voll.api.services.doctor.validations.update;

import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import jakarta.validation.ValidationException;

public class ValidateDotorDisabled  implements  ValidationUpdateDoctor{
    @Override
    public void validation(DataUpdateDoctor data) {
        Boolean doctorActivated = repository.findActivatedById(id);
        if(!doctorActivated){
            throw new ValidationException("The required doctor already disabled");
        }
    }
}
