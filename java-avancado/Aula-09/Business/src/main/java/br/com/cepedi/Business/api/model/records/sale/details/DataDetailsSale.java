package br.com.cepedi.Business.api.model.records.sale.details;

import br.com.cepedi.Business.api.model.entitys.Sale;
import br.com.cepedi.Business.api.model.records.client.details.DataDetailsClient;
import br.com.cepedi.Business.api.model.records.employee.details.DataDetailsEmployee;
import br.com.cepedi.Business.api.model.records.saleProduct.details.DataDetailsSaleProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DataDetailsSale (


    Long id,


    Long idClient,

    Long idEmployee,

    BigDecimal grossValue,

    BigDecimal valueDiscount,

    BigDecimal finalValue,

    LocalDateTime dateTime,


    List<DataDetailsSaleProduct> products

){

    public  DataDetailsSale(Sale sale){
        this(
                sale.getId(),
                sale.getClient().getId(),
                sale.getEmployee().getId(),
                sale.getGrossValue(),
                sale.getValueDiscount(),
                sale.getFinalValue(),
                sale.getDateRegister(),
                sale.getProducts().stream()
                        .map(DataDetailsSaleProduct::new)
                        .collect(Collectors.toList())
        );
    }
}