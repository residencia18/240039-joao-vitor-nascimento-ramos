package br.com.cepedi.Business.api.model.entitys;


import br.com.cepedi.Business.api.model.records.stock.input.DataRegisterStock;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Stock {


    private BigDecimal amount;

    private BigDecimal amountCritical;

    public Stock(DataRegisterStock stock) {
        this.amount = stock.amount();
        this.amountCritical = stock.amountCritical();
    }


}
