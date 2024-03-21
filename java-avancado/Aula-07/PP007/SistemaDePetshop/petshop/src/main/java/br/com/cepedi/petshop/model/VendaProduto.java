package br.com.cepedi.petshop.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="VENDA_PRODUTO")
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter
public class VendaProduto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="ID_VENDA", nullable = false) 
    private Venda venda; 
    
    @ManyToOne
    @JoinColumn(name="ID_PRODUTO", nullable = false)
    private Produto produto;
    
    @Column(name="QUANTIDADE")
    private BigInteger quantidade;


}
