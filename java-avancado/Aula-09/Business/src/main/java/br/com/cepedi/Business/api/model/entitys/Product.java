package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.parameters.P;

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

    public void logicalDelete() {
        this.activated = false;
    }


    public void updateData(DataUpdateProduct data, ProductType productType , Supplier supplier) {

        if(data.code() != null && !data.code().isEmpty()){
            this.code = data.code();
        }

        if(data.name() != null && !data.name().isEmpty()){
            this.name = data.name();
        }

        if(data.color() != null && !data.code().isEmpty()){
            this.color = data.color();
        }

        if(data.description() != null && !data.description().isEmpty()){
            this.description = data.description();
        }


        if(data.price() != null){
            this.price = new Price(data.price());
        }

        if(data.stock() != null){
            this.stock = new Stock(data.stock());
        }

        if(productType != null){
            this.productType = productType;
        }

        if(supplier != null){
            this.supplier = supplier;
        }


    }
}
