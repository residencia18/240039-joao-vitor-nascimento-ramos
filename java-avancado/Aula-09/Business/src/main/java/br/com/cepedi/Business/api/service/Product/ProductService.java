package br.com.cepedi.Business.api.service.Product;


import br.com.cepedi.Business.api.model.entitys.Product;
import br.com.cepedi.Business.api.model.entitys.ProductType;
import br.com.cepedi.Business.api.model.entitys.Supplier;

import br.com.cepedi.Business.api.model.records.client.details.DataDetailsClient;
import br.com.cepedi.Business.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Business.api.model.records.product.details.DataDetailsProduct;
import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import br.com.cepedi.Business.api.model.records.supplier.details.DataDetailsSupplier;
import br.com.cepedi.Business.api.model.records.supplier.input.DataUpdateSupplier;
import br.com.cepedi.Business.api.repository.ProductRepository;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import br.com.cepedi.Business.api.service.Product.validations.disabled.ValidateProductDisabled;
import br.com.cepedi.Business.api.service.Product.validations.register.ValidateProductRegister;
import br.com.cepedi.Business.api.service.Product.validations.update.ValidateProductUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private List<ValidateProductRegister> validationsRegister;

    @Autowired
    private List<ValidateProductUpdate> validationsUpdate;

    @Autowired
    private List<ValidateProductDisabled> validationsDisabled;


    public DataDetailsProduct register(@Valid DataRegisterProduct data) {
        validationsRegister.forEach(v -> v.validation(data));
        ProductType productType = productTypeRepository.getReferenceById(data.idProductType());
        Supplier supplier = supplierRepository.getReferenceById(data.idSupplier());
        Product product = new Product(data,supplier,productType);
        productRepository.save(product);
        return new DataDetailsProduct(product);
    }

    public Page<DataDetailsProduct> list(Pageable pageable) {
        return productRepository.findAllByActivatedTrue(pageable).map(DataDetailsProduct::new);
    }

    public DataDetailsProduct findById(Long id) {
        Product product  = productRepository.getReferenceById(id);
        return new DataDetailsProduct(product);
    }

    public DataDetailsProduct update(Long id , DataUpdateProduct data) {

        validationsUpdate.forEach(v -> v.validation(id,data));

        ProductType productType = null;
        Supplier supplier = null;

        Product product = productRepository.getReferenceById(id);

        if(data.idProductType() != null){
            productType = productTypeRepository.getReferenceById(data.idProductType());
        }

        if(data.idSupplier() != null){
            supplier = supplierRepository.getReferenceById(data.idSupplier());
        }

        product.updateData(data,productType,supplier);
        return new DataDetailsProduct(product);
    }

    public void disabled(Long id){
        validationsDisabled.forEach(v -> v.validation(id));
        Product product = productRepository.getReferenceById(id);
        product.logicalDelete();
    }



}
