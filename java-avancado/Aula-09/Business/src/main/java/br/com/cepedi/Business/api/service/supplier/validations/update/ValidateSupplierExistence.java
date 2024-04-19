package br.com.cepedi.Business.api.service.supplier.validations.update;

import br.com.cepedi.Business.api.model.records.supplier.input.DataUpdateSupplier;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateSupplierExistence implements ValidateUpdateSupplier{

    @Autowired
    private SupplierRepository repository;

    @Override
    public void validation(Long id, DataUpdateSupplier data) {
        if(!repository.existsById(id)){
            throw  new ValidationException("The required supplier is does not exists");
        }
    }
}
