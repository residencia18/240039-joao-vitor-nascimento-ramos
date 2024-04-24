package br.com.cepedi.Voll.api.model.records.address.details;


import br.com.cepedi.Voll.api.model.entitys.Address;

public record DataDetailsAddress(
        String publicPlace,
        String neighborhood,
        String cep,
        String city,
        String uf,
        String complement,
        String number
) {
    public DataDetailsAddress(Address address) {
        this(address.getPublicPlace(), address.getNeighborhood(), address.getCep(), address.getCity(), address.getUf(), address.getComplement(), address.getNumber());
    }
}