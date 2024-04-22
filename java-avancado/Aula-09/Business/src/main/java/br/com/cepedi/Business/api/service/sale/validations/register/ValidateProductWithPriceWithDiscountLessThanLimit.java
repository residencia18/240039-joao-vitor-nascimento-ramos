package br.com.cepedi.Business.api.service.sale.validations.register;

import br.com.cepedi.Business.api.model.entitys.Product;
import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import br.com.cepedi.Business.api.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidateProductWithPriceWithDiscountLessThanLimit implements ValidationRegisterSale{

    @Autowired
    private ProductRepository repository;

    @Override
    public void validation(DataRegisterSale data) {

        if(data.discount() == null){
            return;
        }

        data.products().forEach(dataP -> {
            Product product = repository.getReferenceById(dataP.idProduct());
            BigDecimal valueGross = product.getPrice().getSalePriceMax();
            BigDecimal valueSmall = product.getPrice().getSalePriceMin();

            BigDecimal discountPercentage = data.discount().divide(BigDecimal.valueOf(100));
            BigDecimal discountFactor = BigDecimal.ONE.subtract(discountPercentage);
            BigDecimal valueWithDiscount = valueGross.multiply(discountFactor);


            if (valueWithDiscount.compareTo(valueSmall) < 0) {
                throw new ValidationException("The discounted value is lower than the minimum possible value.");
            }
        });
    }
}
