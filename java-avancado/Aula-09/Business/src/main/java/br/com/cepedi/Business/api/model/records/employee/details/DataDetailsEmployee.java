package br.com.cepedi.Business.api.model.records.employee.details;

import br.com.cepedi.Business.api.model.entitys.Employee;
import br.com.cepedi.Business.api.model.records.address.details.DataDetailsAddress;
import br.com.cepedi.Business.api.model.records.role.details.DataDetailsRole;

import java.time.LocalDate;

public record DataDetailsEmployee(

        Long id,
        String name,
        String email,
        String cpf,
        LocalDate birthday,
        String phoneNumber,
        DataDetailsRole role,
        Boolean activated,
        DataDetailsAddress address

) {

    public DataDetailsEmployee(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getEmail(), employee.getCpf(), employee.getBirthday() ,employee.getPhoneNumber(),new DataDetailsRole(employee.getRole()) , employee.getActivated(), new DataDetailsAddress(employee.getAddress()));
    }
}
