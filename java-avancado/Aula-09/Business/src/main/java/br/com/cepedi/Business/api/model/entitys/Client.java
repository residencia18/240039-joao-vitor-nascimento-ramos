package br.com.cepedi.Business.api.model.entitys;


import br.com.cepedi.Business.api.model.embeddables.Address;
import br.com.cepedi.Business.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Business.api.model.records.client.input.DataUpdateClient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String cpf;

    private LocalDate birthday;

    private String phoneNumber;

    private Boolean activated;

    @Embedded
    private Address address;



    public Client(DataRegisterClient data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();

        if(data.birthday() != null){
            this.birthday = data.birthday();
        }

        this.phoneNumber = data.phoneNumber();
        this.address = new Address(data.dataRegisterAddress());
        this.activated = true;

    }


    public void updateData(DataUpdateClient data) {
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
    }




    public void logicalDelete() {
        this.activated = false;
    }


}
