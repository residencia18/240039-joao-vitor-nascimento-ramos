package br.com.cepedi.model.pessoa;

import java.io.Serializable;

import br.com.cepedi.exceptions.cartaoPassagem.TipoDePassagemInvalidoException;
import br.com.cepedi.exceptions.passageiro.CartaoInvalidoException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;
import br.com.cepedi.model.passagem.CartaoPassagem;

public class Passageiro extends Pessoa  implements Serializable{
	
	private CartaoPassagem cartao;
	private static final long serialVersionUID = 2L;

	public Passageiro(String nome, String CPF, CartaoPassagem cartao) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, CartaoInvalidoException {
		super(nome, CPF);
		setCartao(cartao);
	}
	
	public Passageiro(String nome, String CPF , int cartao) throws CartaoInvalidoException, TipoDePassagemInvalidoException, NomePessoaInvalidoException, CPFPessoaInvalidoException {
		super(nome, CPF);
		setCartao(new CartaoPassagem(cartao));

	}
	
	public Passageiro() {
		super();
	}
	
	@Override
	public String toString() {
		return "[ID= " + id + " Nome= " + nome + " CPF= " + CPF + " cartao= " + cartao + "]";
	}

	public CartaoPassagem getCartao() {
		return cartao;
	}

	public void setCartao(CartaoPassagem cartao) throws CartaoInvalidoException {
		if(cartao==null) {
			throw new CartaoInvalidoException();
		}
		this.cartao = cartao;
	}
	
	

}
