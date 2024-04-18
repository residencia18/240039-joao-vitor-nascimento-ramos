package br.com.cepedi.Business.api.service.productType;


import br.com.cepedi.Business.api.model.entitys.ProductType;

import br.com.cepedi.Business.api.model.records.productType.details.DataDetailsProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository repository;

    public DataDetailsProductType register(@Valid DataRegisterProductType data, UriComponentsBuilder uriBuilder) {
        ProductType productType = new ProductType(data);
        repository.save(productType);
        return new DataDetailsProductType(productType);
    }


}
