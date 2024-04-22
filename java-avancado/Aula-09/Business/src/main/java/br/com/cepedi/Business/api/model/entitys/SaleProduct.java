package br.com.cepedi.Business.api.model.entitys;


import br.com.cepedi.Business.api.model.records.saleProduct.input.DataRegisterSaleProduct;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sale_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_product")
    Product product;

    Long amount;


    public SaleProduct(Product product , Long amount){
        this.amount = amount;
        this.product = product;
    }


}
