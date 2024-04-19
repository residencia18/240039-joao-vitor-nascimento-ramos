package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    private Boolean activated;



    public ProductType(DataRegisterProductType data){
        this.name = data.name();
        this.activated = true;
    }


}
