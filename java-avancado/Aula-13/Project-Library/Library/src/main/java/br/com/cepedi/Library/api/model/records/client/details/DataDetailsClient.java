package br.com.cepedi.Library.api.model.records.client.details;

import br.com.cepedi.Library.api.model.entitys.Client;
import br.com.cepedi.Library.api.model.records.address.details.DataDetailsAddress;

public record DataDetailsClient(
        Long id,
        String name,
        String email,
        String cpf,
        String phoneNumber,
        Boolean activated,
        DataDetailsAddress address
) {
    public DataDetailsClient(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getCpf(), client.getPhoneNumber(), client.getActivated(), new DataDetailsAddress(client.getAddress()));
    }
}