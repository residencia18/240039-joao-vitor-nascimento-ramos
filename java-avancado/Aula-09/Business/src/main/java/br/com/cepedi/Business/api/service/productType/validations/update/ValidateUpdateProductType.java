package br.com.cepedi.Business.api.service.productType.validations.update;

import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataUpdateProductType;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ValidateUpdateProductType {


    void validation(Long id ,DataUpdateProductType data);
}
