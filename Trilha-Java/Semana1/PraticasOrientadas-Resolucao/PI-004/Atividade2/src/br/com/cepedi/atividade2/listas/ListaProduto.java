package br.com.cepedi.atividade2.listas;

import java.util.ArrayList;

import br.com.cepedi.atividade2.Exceptions.CodigoJaCadastradoException;
import br.com.cepedi.atividade2.Exceptions.ProdutoNaoEncontradoException;
import br.com.cepedi.atividade2.model.Produto;

public class ListaProduto {

	private ArrayList<Produto> produtos;

	public ListaProduto() {
		produtos = new ArrayList<>();
	}
	
	public void adicionaProduto(Produto produto) {
		try {
			if(this.codigoDisponivel(produto.getCodigo())) {
				produtos.add(produto);
                System.out.println("Produto cadastrado com sucesso!");

			}
		}catch(CodigoJaCadastradoException e ) {
			System.err.println("Erro : " + e.getMessage());
		}catch(NullPointerException e ) {
			System.err.println("Erro : Não foi possivel cadastrar produto");
		}


	}
	
	private boolean codigoDisponivel(String codigo) throws CodigoJaCadastradoException {
		boolean encontrado = true;
		
		for(Produto produto : produtos) {
			if(produto.getCodigo().equals(codigo)) {
				encontrado = false;
	            throw new CodigoJaCadastradoException("Codigo : " + codigo.toString() + "já  está registrado registrado");
			}
		}
		return encontrado;
	}
	
	public void mostrarProdutos() {
		for(Produto produto : produtos) {
			System.out.println(produto);
			System.out.println("---------------------------");
		}
	}
	
	public Produto buscarProdutoPorCodigo(String codigoProduto) throws ProdutoNaoEncontradoException {
		for(Produto produto : produtos) {
			if(produto.getCodigo().equals(codigoProduto)) {
				return produto;
			}
		}
		throw new ProdutoNaoEncontradoException("Produto não encontrado");
	}
	
}
