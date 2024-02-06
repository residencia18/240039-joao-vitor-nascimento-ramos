package br.com.cepedi.model.listas;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.cepedi.exceptions.listaJornada.JornadaJaCadastrada;
import br.com.cepedi.exceptions.listaJornada.JornadaNaoEncontrada;
import br.com.cepedi.model.pessoa.Funcionario;
import br.com.cepedi.model.pessoa.Pessoa;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.persistenciaListasArquivos.Persistencia;

public class ListaJornadas extends ArrayList<Jornada>  implements Serializable{
	
	public static final String CAMINHO_ARQUIVO = "./Dados/jornadas.txt";
	private static final long serialVersionUID = 14L;

	public ListaJornadas() {
		super();

	}
	
    public void carregarListaDoArquivo(String CAMINHO_ARQUIVO) {
    	ListaJornadas listaLida = Persistencia.lerDoArquivo(CAMINHO_ARQUIVO);
        if (listaLida != null) {
            this.addAll(listaLida);
        }
		corrigeGeracaoID();
    }
    
    
    private void corrigeGeracaoID() {
    	for(Jornada p : this) {
    		if(p.getId()>Jornada.qtdIDsGerados) {
    			Jornada.qtdIDsGerados=p.getId();
    		}
    	}    	
    }
	
	public void adiciona(Jornada jornada) throws JornadaJaCadastrada {
		
		if(jornada==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(jornadaJaCadastrada(jornada)) {
			throw new JornadaJaCadastrada();
		}
		
		add(jornada);
	}
	
	public Jornada buscar(int id) throws JornadaNaoEncontrada{
		if(id<1) {
			throw new IllegalArgumentException("Insira um id 1 ou superior");
		}
		
		for(Jornada j : this) {
			if(j.getId()==id) {
				return j;
			}
		}
		
		throw new JornadaNaoEncontrada();
	}
	
	public void deletar(int id) throws JornadaNaoEncontrada{
		
		if(id<1) {
			throw new IllegalArgumentException("Insira um id 1 ou superior");
		}
		
		for(Jornada j : this) {
			if(j.getId()==id) {
				remove(j);
				System.out.println("Jornada excluida com sucesso!");
				return;
			}
		}
		
		throw new JornadaNaoEncontrada();
	}
	
	
	public void mostraTodos() {
		for(Jornada j  : this) {
			System.out.println(j);
		}
	}
	
	private boolean jornadaJaCadastrada(Jornada jornada) {
		for(Jornada j : this) {
			if(j.equals(jornada)) {
				return true;
			}
		}
		return false;
	}
	

}
