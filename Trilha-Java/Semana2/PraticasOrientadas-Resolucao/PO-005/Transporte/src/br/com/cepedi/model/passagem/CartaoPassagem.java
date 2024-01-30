package br.com.cepedi.model.passagem;

import java.math.BigDecimal;

import br.com.cepedi.exceptions.cartaoPassagem.SaldoInsuficienteException;
import br.com.cepedi.exceptions.cartaoPassagem.TentativaRecargaIdosoException;
import br.com.cepedi.exceptions.cartaoPassagem.TipoDePassagemInvalidoException;
import br.com.cepedi.exceptions.cartaoPassagem.ValorRecargaInvalidoException;

public class CartaoPassagem {


    private BigDecimal saldo;
    private TipoCartao tipo;

    public CartaoPassagem(TipoCartao tipo) {
    	saldo = new BigDecimal(0.0);
        this.tipo = tipo;
    }
    
    
    public CartaoPassagem(int tipo) throws TipoDePassagemInvalidoException {
    	setTipo(tipo);
    	this.saldo = new BigDecimal(0.0);

    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public TipoCartao getTipo() {
        return tipo;
    }

    public void setTipo(TipoCartao tipo) {
        this.tipo = tipo;
    }
    
    public void setTipo(int tipo) throws TipoDePassagemInvalidoException {
    	if(tipo<1 || tipo > 3) {
    		throw new TipoDePassagemInvalidoException();
    	}
        switch(tipo) {
    	case 1:
    		this.tipo = TipoCartao.TRANSPORTE;
    		break;
    	case 2:
    		this.tipo = TipoCartao.ESTUDANTIL;
    		break;
    	case 3:
    		this.tipo = TipoCartao.IDOSO;
    		break;
    }
    }

    public void recarregar(BigDecimal valorRecarga) throws ValorRecargaInvalidoException, TentativaRecargaIdosoException {
    	
    	if(valorRecarga.compareTo(new BigDecimal("0.0")) < 0) {
    		throw new ValorRecargaInvalidoException();
    	}
    	
    	if(tipo == TipoCartao.IDOSO) {
        	throw new TentativaRecargaIdosoException();
        }
        
        saldo = saldo.add(valorRecarga);
    }

    public void usarPassagem(BigDecimal valorPassagem) throws SaldoInsuficienteException {
        //Passagem gratuita para idoso
    	if (tipo == TipoCartao.IDOSO) {
            return;
        }

        if (tipo == TipoCartao.ESTUDANTIL) {
            valorPassagem = valorPassagem.multiply(BigDecimal.valueOf(0.5));
        }
        
        if(saldoInsuficiente(valorPassagem)) {
        	throw new SaldoInsuficienteException();
        }

        saldo = saldo.subtract(valorPassagem);
    }
    
    private boolean saldoInsuficiente(BigDecimal valorPassagem) {
    	return saldo.compareTo(valorPassagem) < 0;
    }

	@Override
	public String toString() {
		return "[saldo=" + saldo + ", tipo=" + tipo + "]";
	}
    
    


}
