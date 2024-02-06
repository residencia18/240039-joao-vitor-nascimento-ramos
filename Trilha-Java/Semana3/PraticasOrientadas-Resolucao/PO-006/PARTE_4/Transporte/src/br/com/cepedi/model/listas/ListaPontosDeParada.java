package br.com.cepedi.model.listas;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.cepedi.exceptions.listaFuncionarios.FuncionarioNaoEncontrado;
import br.com.cepedi.exceptions.listaPontosDeParada.PontoJaCadastrado;
import br.com.cepedi.exceptions.listaPontosDeParada.PontoNaoEncontrado;
import br.com.cepedi.model.pessoa.Funcionario;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.model.pessoa.Pessoa;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.persistenciaListas.Persistencia;

public class ListaPontosDeParada extends ArrayList<PontoDeParada>  implements Serializable{
	
	private static final long serialVersionUID = 11L;
	public static final String CAMINHO_ARQUIVO = "./Dados/pontosDeParada.txt";

	public ListaPontosDeParada() {
		super();
		carregarListaDoArquivo();
		corrigeGeracaoID();

	}

    private void carregarListaDoArquivo() {
    	ListaPontosDeParada listaLida = Persistencia.lerDoArquivo(CAMINHO_ARQUIVO);
        if (listaLida != null) {
            this.addAll(listaLida);
        }
    }
    
    private void corrigeGeracaoID() {
    	for(PontoDeParada p : this) {
    		if(p.getId()>PontoDeParada.qtdPontosParada) {
    			PontoDeParada.qtdPontosParada=p.getId();
    		}
    	}    	
    }
	public void adiciona(PontoDeParada ponto) throws PontoJaCadastrado  {
		
		if(ponto==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(pontoJaCadastrado(ponto.getNome())) {
			throw new PontoJaCadastrado();
		}

		add(ponto);
			
	}

	public PontoDeParada buscar(String nome) throws PontoNaoEncontrado {
		
		if(nome==null) {
			throw new NullPointerException("Foi inserido um nome nulo na busca.");
		}
		
		for(PontoDeParada p : this) {
			if(p.getNome().equals(nome)) {
				return p;
			}
		}
		
		throw new PontoNaoEncontrado();
	}
	
	public PontoDeParada buscar(int id) throws PontoNaoEncontrado {
		
		if(id < 1) {
			throw new NullPointerException("Foi inserido um id inválido");
		}
		
		for(PontoDeParada p : this) {
			if(p.getId()==id) {
				return p;
			}
		}
		
		throw new PontoNaoEncontrado();
	}
	
	
	public void deletar(String nome) throws PontoNaoEncontrado{
		
		if(nome==null) {
			throw new NullPointerException("Foi inserido um nome nulo na busca.");
		}
		
		
		for(PontoDeParada p : this) {
			if(p.getNome().equals(nome)) {
				this.remove(p);
				System.out.println("Ponto de parada excluido com sucesso!");
				return;
			}
		}
		

		throw new PontoNaoEncontrado();
	}
	
	public void mostraTodos() {
		for(PontoDeParada p : this) {
			System.out.println(p);
		}
	}
	
	
	private boolean pontoJaCadastrado(String nome) {
		for(PontoDeParada p : this) {
			if(p.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}

}