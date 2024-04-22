package br.com.cepedi.Business.api.service.sale.validations.register;

import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import br.com.cepedi.Business.api.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductsExistsForRegisterSale implements ValidationRegisterSale {

    @Autowired
    private ProductRepository repository;


    @Override
    public void validation(DataRegisterSale data) {
        data.products().forEach(p -> {
            if (!repository.existsById(p.idProduct())) {
                throw new ValidationException("Product não with id: " + p.idProduct() + " not exists");
            }
        });
    }
}
