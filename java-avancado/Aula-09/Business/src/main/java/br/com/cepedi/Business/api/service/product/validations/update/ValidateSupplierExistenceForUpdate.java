package br.com.cepedi.Business.api.service.product.validations.update;

import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateSupplierExistenceForUpdate implements ValidateProductUpdate{

    @Autowired
    private SupplierRepository repository;

    @Override
    public void validation(Long id , DataUpdateProduct data) {
        if(data.idSupplier() != null){
            if(!repository.existsById(data.idSupplier())){
                throw  new ValidationException("The required client is does not exists");
            }
        }
    }
}
