package br.com.cepedi.Business.api.service.Product.validations.register;

import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateSupplierExistenceForRegisterProduct implements ValidateProductRegister{

    @Autowired
    private SupplierRepository repository;

    @Override
    public void validation(DataRegisterProduct data) {
        if(!repository.existsById(data.idSupplier())){
            throw  new ValidationException("The required client is does not exists");
        }
    }
}
