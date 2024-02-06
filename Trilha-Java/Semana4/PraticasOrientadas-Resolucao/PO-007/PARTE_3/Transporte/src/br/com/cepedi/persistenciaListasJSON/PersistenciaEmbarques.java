package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.listaEmbarque.EmbarqueJaRegistrado;
import br.com.cepedi.exceptions.listaJornada.JornadaNaoEncontrada;
import br.com.cepedi.exceptions.listaPassageiros.PassageiroNaoEncontrado;
import br.com.cepedi.exceptions.listaPontosDeParada.PontoNaoEncontrado;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoNaoEncontrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoNaoEncontrado;
import br.com.cepedi.model.listas.ListaEmbarques;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.model.transporte.Embarque;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;

public abstract class PersistenciaEmbarques {
	
	public static void salvarArquivo(ListaEmbarques embarques) {

	    JSONArray json = new JSONArray();

	    for (Embarque embarque : embarques) {
	        JSONObject o = new JSONObject();
	        o.put("idPassageiro", embarque.getPassageiro().getId());
	        o.put("idJornada", embarque.getJornada().getId());
	        o.put("idTrajeto", embarque.getTrajeto().getId());
	        o.put("idTrecho", embarque.getTrecho().getId());
	        o.put("idPonto", embarque.getPonto().getId());
	        json.put(o);
	    }

	    try {
	        FileWriter fw = new FileWriter("./DadosJSON/LEmbarques.json");
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(json.toString());
	        bw.close();
	    } catch (IOException e) {
	        System.out.println("Erro ao abrir arquivo de embarques para escrita: " + e.getMessage());
	    }
	    
	}
	
	public static void lerArquivo(ListaEmbarques embarques , ListaPassageiros passageiros , ListaJornadas jornadas , ListaTrajetos trajetos , ListaTrechos trechos , ListaPontosDeParada pontos) throws JornadaNaoEncontrada, PassageiroNaoEncontrado, TrajetoNaoEncontrado, TrechoNaoEncontrado, PontoNaoEncontrado, EmbarqueJaRegistrado {
	    JSONArray json = new JSONArray();
	    try {
	        FileReader fr = new FileReader("./DadosJSON/LEmbarques.json");
	        BufferedReader br = new BufferedReader(fr);
	        if (!br.ready()) {
	            return;
	        }
	        json = new JSONArray(br.readLine());
	        br.close();
	    } catch (IOException e) {
	        System.out.println("Erro ao abrir arquivo de embarques para leitura: " + e.getMessage());
	    }

	    for (int i = 0; i < json.length(); i++) {
	        int idJornada = json.getJSONObject(i).getInt("idJornada");
	        int idTrajeto = json.getJSONObject(i).getInt("idTrajeto");
	        int idPassageiro = json.getJSONObject(i).getInt("idPassageiro");
	        int idTrecho = json.getJSONObject(i).getInt("idTrecho");
	        int idPonto = json.getJSONObject(i).getInt("idPonto");

	        Jornada jornada = jornadas.buscar(idJornada);
	        Passageiro passageiro = passageiros.buscar(idPassageiro);
	        Trajeto trajeto = trajetos.buscar(idTrajeto);
	        Trecho trecho = trechos.bucar(idTrecho);
	        PontoDeParada ponto = pontos.buscar(idPonto);
	        
	        Embarque embarque = new Embarque(passageiro,jornada,trajeto,trecho,ponto);



	        embarques.adiciona(embarque);
	    }
	    
	}

}
