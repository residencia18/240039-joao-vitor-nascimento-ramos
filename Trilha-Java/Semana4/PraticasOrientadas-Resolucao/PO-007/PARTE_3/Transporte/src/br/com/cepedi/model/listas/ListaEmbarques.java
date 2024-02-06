package br.com.cepedi.model.listas;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.cepedi.exceptions.listaEmbarque.EmbarqueJaRegistrado;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.model.transporte.Embarque;
import br.com.cepedi.persistenciaListasArquivos.Persistencia;

public class ListaEmbarques extends ArrayList<Embarque>  implements Serializable{

	private static final long serialVersionUID = 16L;

	
	public ListaEmbarques() {
	}


	
	
	public void adiciona(Embarque embarque) throws EmbarqueJaRegistrado {
		
		if(embarque==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(embarqueJaRegistrado(embarque)) {
			throw new EmbarqueJaRegistrado();
		}

		add(embarque);
			
	}
	
	
	private boolean embarqueJaRegistrado(Embarque embarque) {
		for(Embarque e : this) {
			if(e.equals(embarque)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void mostraTodos() {
		for(Embarque e : this) {
			System.out.println(e);
		}
	}
	
	public void mostraEmbarquesPorPassageiro(Passageiro p) {
		for(Embarque e : this) {
			if(p.getNome().equals(e.getPassageiro().getNome())) {
				System.out.println(e);
			}
		}
	}
	
	
	
}
