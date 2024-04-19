package br.com.cepedi.Business.api.service.Product.validations.update;

import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ValidateProductUpdate {


    void validation(Long id , DataUpdateProduct data);
}
