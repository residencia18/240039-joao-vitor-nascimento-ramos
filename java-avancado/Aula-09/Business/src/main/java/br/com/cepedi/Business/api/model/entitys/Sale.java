package br.com.cepedi.Business.api.model.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    private BigDecimal grossValue;

    private BigDecimal valueDiscount;

    private BigDecimal finalValue;

    private LocalDateTime dateRegister;


    @OneToMany(fetch = FetchType.EAGER , mappedBy = "sale")
    List<SaleProduct> products;


    public Sale(Client client , Employee employee , List<SaleProduct> products , BigDecimal valueDiscount){
        this.employee = employee;
        this.client = client;
        this.products = products;
        this.dateRegister = LocalDateTime.now();
        this.grossValue = calculeGrossValue(products);
        this.finalValue = calculeFinalValue(products,valueDiscount);
        this.valueDiscount = valueDiscount;

    }

    public Sale(Client client , Employee employee , List<SaleProduct> products ){

        this.employee = employee;
        this.client = client;
        this.products = products;
        this.dateRegister = LocalDateTime.now();
        this.grossValue = calculeGrossValue(products);
        this.finalValue = this.grossValue;



    }

    private BigDecimal calculeGrossValue(List<SaleProduct> products){
        BigDecimal grossValue = BigDecimal.ZERO;

        for (SaleProduct saleProduct : products) {
            BigDecimal valueUnit = saleProduct.getProduct().getPrice().getSalePriceMax();
            BigDecimal amount = new BigDecimal(saleProduct.getAmount());
            grossValue = grossValue.add(valueUnit.multiply(amount));
        }

        return grossValue;
    }

    private BigDecimal calculeFinalValue(List<SaleProduct> products , BigDecimal valueDiscount){
        BigDecimal finalValue = BigDecimal.ZERO;

        for (SaleProduct saleProduct : products) {
            BigDecimal valueGross = saleProduct.getProduct().getPrice().getSalePriceMax();
            BigDecimal discountPercentage = valueDiscount.divide(BigDecimal.valueOf(100));
            BigDecimal discountFactor = BigDecimal.ONE.subtract(discountPercentage);
            BigDecimal valueWithDiscount = valueGross.multiply(discountFactor);
            finalValue = finalValue.add(valueWithDiscount.multiply(new BigDecimal(saleProduct.getAmount())));
        }

        return finalValue;
    }




}
