package br.com.cepedi.Business.api.service.Product;


import br.com.cepedi.Business.api.model.entitys.Product;
import br.com.cepedi.Business.api.model.entitys.ProductType;
import br.com.cepedi.Business.api.model.entitys.Supplier;

import br.com.cepedi.Business.api.model.records.product.details.DataDetailsProduct;
import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.repository.ProductRepository;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public DataDetailsProduct register(@Valid DataRegisterProduct data) {
        ProductType productType = productTypeRepository.getReferenceById(data.idProductType());
        Supplier supplier = supplierRepository.getReferenceById(data.idSupplier());
        Product product = new Product(data,supplier,productType);
        productRepository.save(product);
        return new DataDetailsProduct(product);
    }


}
