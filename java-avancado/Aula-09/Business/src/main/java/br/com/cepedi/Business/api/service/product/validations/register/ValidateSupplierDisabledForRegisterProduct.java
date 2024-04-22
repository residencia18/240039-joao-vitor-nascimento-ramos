package br.com.cepedi.Business.api.service.product.validations.register;

import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateSupplierDisabledForRegisterProduct implements ValidateProductRegister{

    @Autowired
    private SupplierRepository repository;

    @Override
    public void validation(DataRegisterProduct data) {
        Boolean activated = repository.findActivatedById(data.idSupplier());
        if(!activated){
            throw new ValidationException("The required supplier is disabled");
        }
    }
}
