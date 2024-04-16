package br.com.cepedi.Library.api.model.embeddables;

import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {

    private String publicPlace;

    private String neighborhood;

    private String cep;

    private String city;

    private String uf;

    private String complement;

    private String number;

    public Address(DataRegisterAddress dataRegisterAddress) {
        this.publicPlace = dataRegisterAddress.publicPlace();
        this.neighborhood = dataRegisterAddress.neighborhood();
        this.cep = dataRegisterAddress.cep();
        this.city = dataRegisterAddress.city();
        this.uf = dataRegisterAddress.uf();
        this.complement = dataRegisterAddress.complement();
        this.number = dataRegisterAddress.number();
    }


    public void updateData(DataRegisterAddress data) {
        if (data.publicPlace() != null) {
            this.publicPlace = data.publicPlace();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.uf() != null) {
            this.uf = data.uf();
        }
        if (data.complement() != null) {
            this.complement = data.complement();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
    }

}
