package br.com.cepedi.Library.api.model.entitys;

import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import jakarta.persistence.*;
import lombok.*;

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

    private String phoneNumber;

    private Boolean activated;

    @Embedded
    private Address address;

    public Client(DataRegisterClient data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.phoneNumber = data.phoneNumber();
        this.address = new Address(data.dataAddress());
        this.activated = true;

    }


    public void updateData(DataUpdateClient data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.email() != null) {
            this.email = data.email();
        }

        if (data.cpf() != null) {
            this.cpf = data.cpf();
        }

        if (data.phoneNumber() != null) {
            this.phoneNumber = data.phoneNumber();
        }

        if (data.dataAddress() != null) {
            this.address = new Address(data.dataAddress());
        }
    }




    public void logicalDelete() {
        this.activated = false;
    }


}
