package br.com.cepedi.vercedoresFormula1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Utils {

    protected static <T extends Comparable<T>> Map<String, T> ordenarPorValor(Map<String, T> mapa) {
        List<Map.Entry<String, T>> listaEntradas = new ArrayList<>(mapa.entrySet());

        Collections.sort(listaEntradas, new Comparator<Map.Entry<String, T>>() {
            @Override
            public int compare(Map.Entry<String, T> entrada1, Map.Entry<String, T> entrada2) {
                return entrada2.getValue().compareTo(entrada1.getValue());
            }
        });

        Map<String, T> mapaOrdenado = new LinkedHashMap<>();
        for (Map.Entry<String, T> entrada : listaEntradas) {
            mapaOrdenado.put(entrada.getKey(), entrada.getValue());
        }
        return mapaOrdenado;
    }
	
    protected static <K, V> boolean verificarChave(Map<K, V> mapa, K chave) {
        return mapa.containsKey(chave);
    }

    protected static <K> void adicionaValor(Map<K, Integer> mapa, K chave, int valor) {
        if (mapa.containsKey(chave)) {
            int valorAtual = mapa.get(chave);
            mapa.put(chave, valorAtual + valor);
        }
    }
    
}
