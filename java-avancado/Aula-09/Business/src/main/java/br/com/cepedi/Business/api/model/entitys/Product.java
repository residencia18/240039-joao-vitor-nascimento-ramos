package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String description;

    private String color;

    @Embedded
    private Price price;

    @Embedded
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "id_product_type")
    private ProductType productType;

    private Boolean activated;

    public  Product(DataRegisterProduct data, Supplier supplier , ProductType productType){
        this.code = data.code();
        this.name = data.name();
        this.description = data.description();
        this.color = data.color();
        this.price = new Price(data.price());
        this.stock = new Stock(data.stock());
        this.supplier = supplier;
        this.productType = productType;
        this.activated = true;
    }


}
