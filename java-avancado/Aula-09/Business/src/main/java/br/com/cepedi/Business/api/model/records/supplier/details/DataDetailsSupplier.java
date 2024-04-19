package br.com.cepedi.Business.api.model.records.supplier.details;

import br.com.cepedi.Business.api.model.entitys.Supplier;
import br.com.cepedi.Business.api.model.records.address.details.DataDetailsAddress;

public record DataDetailsSupplier(
        Long id,

        String name,
        String CPF,
        String CNPJ,
        String email,
        String phoneNumber1,
        String phoneNumber2,
        DataDetailsAddress address
) {
    public DataDetailsSupplier(Supplier supplier) {
        this(
                supplier.getId(),
                supplier.getName(),
                supplier.getCPF(),
                supplier.getCNPJ(),
                supplier.getEmail(),
                supplier.getPhoneNumber1(),
                supplier.getPhoneNumber2(),
                new DataDetailsAddress(supplier.getAddress())
        );
    }
}