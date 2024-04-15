package br.com.cepedi.Business.api.model.entitys;


import br.com.cepedi.Business.api.model.embeddables.Address;
import br.com.cepedi.Business.api.model.records.supplier.input.DataRegisterSupplier;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String CPF;

    private String CNPJ;

    private String email;

    private String phoneNumber1;

    private String phoneNumber2;

    @Embedded
    private Address address;


    public Supplier(DataRegisterSupplier data){

        this.name = data.name();

        if (data.CPF() != null){
            this.CPF = data.CPF();
        }

        if(data.CNPJ() != null){
            this.CNPJ = data.CNPJ();
        }

        this.email = data.email();

        this.phoneNumber1 = data.phoneNumber1();

        if(data.phoneNumber2() != null){
            this.phoneNumber2 = data.phoneNumber2();
        }

        this.address = new Address(data.dataRegisterAddress());
    }

}
