package br.com.cepedi.vercedoresFormula1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.cepedi.vercedoresFormula1.model.WinnerTDO;

public abstract class QuerysWinners {
	
    protected static List<WinnerTDO> getTopNVencedores(int n , List<WinnerTDO> vencedores) {
        List<WinnerTDO> winnersOrderByVictorys = new ArrayList<>(vencedores);
        Collections.sort(winnersOrderByVictorys, Collections.reverseOrder());
        return winnersOrderByVictorys.subList(0, Math.min(n, winnersOrderByVictorys.size()));
    }

}
