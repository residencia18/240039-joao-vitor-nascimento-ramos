package br.com.cepedi.Business.api.model.records.address.details;

import br.com.cepedi.Business.api.model.Enums.State;
import br.com.cepedi.Business.api.model.embeddables.Address;

public record DataDetailsAddress(
        String publicPlace,
        String neighborhood,
        String cep,
        String city,
        State state,
        String complement,
        String number
) {
    public DataDetailsAddress(Address address) {
        this(
                address.getPublicPlace(),
                address.getNeighborhood(),
                address.getCep(),
                address.getCity(),
                address.getState(),
                address.getComplement(),
                address.getNumber()
        );
    }
}
