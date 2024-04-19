package br.com.cepedi.Business.api.service.productType;


import br.com.cepedi.Business.api.model.entitys.ProductType;

import br.com.cepedi.Business.api.model.records.productType.details.DataDetailsProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataUpdateProductType;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import br.com.cepedi.Business.api.service.productType.validations.disabled.ValidateDisabledProductType;
import br.com.cepedi.Business.api.service.productType.validations.update.ValidateUpdateProductType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository repository;

    @Autowired
    private List<ValidateUpdateProductType> validationsUpdate;

    @Autowired
    private List<ValidateDisabledProductType> validationsDisabled;

    public DataDetailsProductType register(@Valid DataRegisterProductType data) {
        ProductType productType = new ProductType(data);
        repository.save(productType);
        return new DataDetailsProductType(productType);
    }

    public Page<DataDetailsProductType> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsProductType::new);
    }

    public DataDetailsProductType findById(Long id) {
        ProductType productType = repository.getReferenceById(id);
        return new DataDetailsProductType(productType);

    }

    public DataDetailsProductType update(Long id , DataUpdateProductType data) {
        validationsUpdate.forEach(v -> v.validation(id,data));
        ProductType productType  = repository.getReferenceById(id);
        productType.updateData(data);
        return new DataDetailsProductType(productType);
    }

    public void disabled(Long id){
        validationsDisabled.forEach(v -> v.validation(id));
        ProductType productType  = repository.getReferenceById(id);
        productType.logicalDelete();
    }


}
