package br.com.cepedi.Business.api.service.productType;


import br.com.cepedi.Business.api.model.entitys.Client;
import br.com.cepedi.Business.api.model.entitys.ProductType;

import br.com.cepedi.Business.api.model.records.client.details.DataDetailsClient;
import br.com.cepedi.Business.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Business.api.model.records.productType.details.DataDetailsProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<DataDetailsProductType> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsProductType::new);
    }

    public DataDetailsProductType findById(Long id) {
        ProductType productType = repository.getReferenceById(id);
        return new DataDetailsProductType(productType);

    }

//    public DataDetailsProductType update(DataUpdateClient data) {
//        ProductType productType  = repository.getReferenceById(data.id());
//        client.updateData(data);
//        return new DataDetailsProductType(client);
//    }
//
//    public void disabled(Long id){
//        Client client = repository.getReferenceById(id);
//        client.logicalDelete();
//    }


}
