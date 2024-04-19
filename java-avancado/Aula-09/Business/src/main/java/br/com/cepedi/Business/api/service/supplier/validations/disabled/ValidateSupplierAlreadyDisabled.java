package br.com.cepedi.Business.api.service.supplier.validations.disabled;

import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateSupplierAlreadyDisabled implements ValidateDisabledSupplier{

    @Autowired
    private SupplierRepository repository;

    @Override
    public void validation(Long id) {
        Boolean activated = repository.findActivatedById(id);
        if(!activated){
            throw new ValidationException("The required product type already disabled");
        }
    }
}
