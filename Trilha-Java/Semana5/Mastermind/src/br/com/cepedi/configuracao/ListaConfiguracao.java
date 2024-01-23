package br.com.cepedi.configuracao;

import java.io.File;
import java.util.ArrayList;

import br.com.cepedi.exceptions.ConfiguracaoDuplicadaException;


public class ListaConfiguracao {
	
	public static final String CAMINHO = "."+File.separator+"arquivos"+File.separator;
	
	private String nomeArquivo;
	private ArrayList<Configuracao> lista;
	public ListaConfiguracao() {
		super();
		nomeArquivo = CAMINHO+"configs.json";
		lista = new ArrayList<Configuracao>();
	}
	
	

	public ArrayList<Configuracao> getLista() {
		return lista;
	}

	public void novaConfiguracao(Configuracao config) {
        if (!existeConfiguracaoComNome(config.getNome())) {
            lista.add(config);
        } else {
        	throw new ConfiguracaoDuplicadaException();
        }
    }

    private boolean existeConfiguracaoComNome(String nome) {
        // Verifica se existe uma configuração com o mesmo nome na lista
        for (Configuracao c : lista) {
            if (c.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
	
	public void mostraConfiguracoes() {
		for(Configuracao c : lista) {
			System.out.println(c);
		}
	}
	
}
