package br.com.cepedi.Business.api.service.Product.validations.update;

import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import br.com.cepedi.Business.api.repository.ProductRepository;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductTypeExistenceForUpdate implements ValidateProductUpdate{

    @Autowired
    private ProductTypeRepository repository;

    @Override
    public void validation(Long id ,DataUpdateProduct data) {
        if(data.idProductType() != null){
            if(!repository.existsById(data.idProductType())){
                throw new ValidationException("The required type product does not exists");
            }
        }
    }
}
