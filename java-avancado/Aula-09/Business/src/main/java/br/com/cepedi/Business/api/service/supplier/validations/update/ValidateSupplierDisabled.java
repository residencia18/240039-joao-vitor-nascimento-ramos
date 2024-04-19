package br.com.cepedi.Business.api.service.supplier.validations.update;

import br.com.cepedi.Business.api.model.records.supplier.input.DataUpdateSupplier;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateSupplierDisabled implements ValidateUpdateSupplier{

    @Autowired
    private SupplierRepository repository;

    @Override
    public void validation(Long id, DataUpdateSupplier data) {
        Boolean activated = repository.findActivatedById(id);
        if(!activated){
            throw new ValidationException("The required supplier already disabled");
        }
    }
}
