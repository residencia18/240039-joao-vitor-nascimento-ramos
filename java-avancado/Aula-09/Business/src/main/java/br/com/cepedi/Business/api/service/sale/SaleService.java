package br.com.cepedi.Business.api.service.sale;


import br.com.cepedi.Business.api.model.entitys.*;
import br.com.cepedi.Business.api.model.records.sale.details.DataDetailsSale;
import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import br.com.cepedi.Business.api.repository.*;
import br.com.cepedi.Business.api.service.sale.validations.register.ValidationRegisterSale;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    List<ValidationRegisterSale> validationsRegister;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    SaleProductRepository saleProductRepository;


    public DataDetailsSale register(@Valid DataRegisterSale data) {
        validationsRegister.forEach(v -> v.validation(data));

        List<SaleProduct> products = new ArrayList<>();

        Client client = clientRepository.getReferenceById(data.idClient());
        Employee employee = employeeRepository.getReferenceById(data.idEmployee());
        Sale sale;

        data.products().forEach(p -> {
            Product product = productRepository.getReferenceById(p.idProduct());
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProduct(product);
            saleProduct.setAmount(p.amount());
            products.add(saleProduct);
        });


        if(data.discount() != null){
            BigDecimal discount = data.discount();
            sale = new Sale(client,employee,products,discount);
        }else{
            sale = new Sale(client,employee,products);
        }


        sale.getProducts().forEach(p ->{
            p.setSale(sale);
            saleProductRepository.save(p);
        } );
        saleRepository.save(sale);

        return new DataDetailsSale(sale);

    }




}
