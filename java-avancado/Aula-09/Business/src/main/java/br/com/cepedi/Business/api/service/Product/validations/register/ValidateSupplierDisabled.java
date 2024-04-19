package br.com.cepedi.Business.api.service.Product.validations.register;

import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateSupplierDisabled implements ValidateProductRegister{

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
