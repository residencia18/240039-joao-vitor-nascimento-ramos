package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.embeddables.Address;
import br.com.cepedi.Business.api.model.records.employee.input.DataRegisterEmployee;
import br.com.cepedi.Business.api.model.records.employee.input.DataUpdateEmployee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Role role;

    private String email;

    private String cpf;

    private LocalDate birthday;

    private String phoneNumber;

    @Embedded
    private Address address;

    private Boolean activated;



    public Employee(DataRegisterEmployee data, Role role) {
        this.name = data.name();
        this.role = role;
        this.email = data.email();
        this.cpf = data.cpf();
        this.birthday = data.birthday();
        this.phoneNumber = data.phoneNumber();
        this.address = new Address(data.dataRegisterAddress());
        this.role = role;
        this.activated = true;
    }



    public void logicalDelete() {
        this.activated = false;
    }

    public void updateData(DataUpdateEmployee data, Role role) {

        if (data.name() != null && !data.name().isEmpty()) {
            this.name = data.name();
        }

        if (data.email() != null && !data.email().isEmpty()) {
            this.email = data.email();
        }

        if (data.cpf() != null && !data.cpf().isEmpty()) {
            this.cpf = data.cpf();
        }

        if (data.phoneNumber() != null && !data.phoneNumber().isEmpty()) {
            this.phoneNumber = data.phoneNumber();
        }

        if (data.dataRegisterAddress() != null && !data.phoneNumber().isEmpty()) {
            this.address = new Address(data.dataRegisterAddress());
        }

        if(role != null){
            this.role = role;
        }


    }

}

