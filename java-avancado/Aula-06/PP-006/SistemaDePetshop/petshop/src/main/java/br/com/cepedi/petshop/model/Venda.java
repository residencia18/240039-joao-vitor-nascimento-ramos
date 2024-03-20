package br.com.cepedi.petshop.model;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;

import br.com.cepedi.petshop.exceptions.PrecoInvalidoException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="VENDA")
@Getter
@ToString
@Setter
public class Venda {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	
	@NotNull
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE" , nullable = false)
	private Cliente cliente;
	
	
	@Column(name="VALOR_TOTAL")
	private BigDecimal valor_total;


	public Venda() {
	    super();
	    this.valor_total = BigDecimal.ZERO;
	}



	
    public void adicionarValor(BigDecimal valor) throws PrecoInvalidoException {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.valor_total = this.valor_total.add(valor);
        }else {
        	throw new PrecoInvalidoException();
        }
    }
    
    public void retiraValor(BigDecimal valor) throws PrecoInvalidoException {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.valor_total = this.valor_total.subtract(valor);
        }else {
        	throw new PrecoInvalidoException();
        }
    }
	
	
}
