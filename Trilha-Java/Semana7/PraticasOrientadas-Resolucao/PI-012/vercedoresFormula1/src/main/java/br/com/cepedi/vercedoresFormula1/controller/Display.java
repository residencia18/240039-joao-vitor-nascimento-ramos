package br.com.cepedi.vercedoresFormula1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.cepedi.vercedoresFormula1.model.WinnerTDO;

public abstract class Display {
	
    protected static String exibirVencedores(List<WinnerTDO> winners) {
        StringBuilder saida = new StringBuilder("<html><body>");
        for (WinnerTDO winnerTDO : winners) {
            saida.append(winnerTDO).append("<br>");
        }
        saida.append("</body></html>");
        return saida.toString();
    }
    
    protected static <T> String exibirPaises(Map<String,T> listPorPais) {
        StringBuilder saida = new StringBuilder("<html><body>");
    	for (String pais : listPorPais.keySet()) {
            T valor = listPorPais.get(pais);
            saida.append("Contry: " + pais + ", Vectorys: " + valor).append("<br>");
        }
        saida.append("</body></html>");
        return saida.toString();
    }



}
