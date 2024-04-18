package br.com.cepedi.Business.api.model.records.client.details;

import br.com.cepedi.Business.api.model.entitys.Client;
import br.com.cepedi.Business.api.model.records.address.details.DataDetailsAddress;

import java.time.LocalDate;

public record DataDetailsClient(
        Long id,
        String name,
        String email,
        String cpf,
        LocalDate birthday,
        String phoneNumber,
        Boolean activated,
        DataDetailsAddress address
) {
    public DataDetailsClient(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getCpf(), client.getBirthday() ,client.getPhoneNumber(), client.getActivated(), new DataDetailsAddress(client.getAddress()));
    }
}
