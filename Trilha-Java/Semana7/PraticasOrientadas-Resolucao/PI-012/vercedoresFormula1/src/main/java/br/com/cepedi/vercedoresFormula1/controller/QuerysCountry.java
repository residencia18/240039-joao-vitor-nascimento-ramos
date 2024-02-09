package br.com.cepedi.vercedoresFormula1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cepedi.vercedoresFormula1.model.WinnerTDO;

public abstract class QuerysCountry {

    protected static List<WinnerTDO> filtrarVencedoresPorPais(String pais , List<WinnerTDO> vencedores) {
        List<WinnerTDO> vencedoresFiltrados = new ArrayList<>();
        for (WinnerTDO winnerTDO : vencedores) {
            if (winnerTDO.getCountry().equals(pais)) {
                vencedoresFiltrados.add(winnerTDO);
            }
        }
        return vencedoresFiltrados;
    }
    
    protected static Map<String,Integer> victoryPorPais(List<WinnerTDO> vencedores) {
    	Map<String,Integer> lista = new HashMap<>();
    	String nomePais;
    	int quantidadeAtual;
    	for (WinnerTDO winnerTDO : vencedores) {
    		nomePais = winnerTDO.getCountry();
			if(Utils.verificarChave(lista, nomePais)){
				quantidadeAtual = lista.get(nomePais);
				Utils.adicionaValor(lista, nomePais, winnerTDO.getQtdVictory());
			}else {
				lista.put(nomePais, winnerTDO.getQtdVictory());
			}
		}
    	return lista;
    }
    
    protected static Map<String,Integer> numVencedoresPorPais(List<WinnerTDO> vencedores) {
    	Map<String,Integer> lista = new HashMap<>();
    	String nomePais;
    	int quantidadeAtual;
    	for (WinnerTDO winnerTDO : vencedores) {
    		nomePais = winnerTDO.getCountry();
			if(lista.containsKey(nomePais)){
				quantidadeAtual = lista.get(nomePais);
				lista.put(nomePais, quantidadeAtual+1);
			}else {
				lista.put(nomePais, 1);
			}
		}
    	return lista;
    }
    
    protected static Map<String,Double> buscaMediaPorPais(Map<String, Integer> victoryPorPais , List<WinnerTDO> vencedores){
    	Map<String,Integer> numVencedoresPorPais = QuerysCountry.numVencedoresPorPais(vencedores);
    	Map<String,Double> mediaPorPais = new HashMap<>();
    	for (String pais : victoryPorPais.keySet()) {
            double media = (double) victoryPorPais.get(pais) / numVencedoresPorPais.get(pais);
            mediaPorPais.put(pais, media);
        }
    	return mediaPorPais;
    }
    

 
}
