package br.com.cepedi.Business.api.service.sale.validations.register;

import br.com.cepedi.Business.api.model.entitys.Product;
import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import br.com.cepedi.Business.api.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
public class ValidationStockOfProductForRegisterSale implements ValidationRegisterSale{

    @Autowired
    private ProductRepository repository;

    @Override
    public void validation(DataRegisterSale data) {
        data.products().forEach(p -> {
            if (repository.findStockById(p.idProduct()).compareTo(new BigDecimal(p.amount())) < 0) {
                throw new ValidationException("Product não with id: " + p.idProduct() + " has amount less than " + p.amount());
            }
        });
    }
}
