package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.listaPontosDeParada.PontoNaoEncontrado;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoJaCadastrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoJaCadastrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoNaoEncontrado;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.transporte.Checkpoint;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;

public abstract class PersistenciaTrajetos {
	
	
	public static void salvarArquivo(ListaTrajetos trajetos) {
		
		JSONArray json = new JSONArray();

		for (Trajeto trajeto : trajetos) {
			JSONObject o = new JSONObject();
			o.put("id", trajeto.getId());
			o.put("idCheckpoint", trajeto.getCheckpoint().getPonto().getId());
			o.put("tempoChegada", trajeto.getCheckpoint().getHoraChegada());
			
			
			JSONArray trechosArray = new JSONArray();
            for (Trecho trecho : trajeto.getTrechos()) {
                JSONObject trechoJSON = new JSONObject();
                trechoJSON.put("trecho", trecho.getId());
                trechosArray.put(trechoJSON);
            }
            o.put("trechos", trechosArray);

            json.put(o);
					}

		try {
			FileWriter fw = new FileWriter("./DadosJSON/LTrajetos.json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de trechos para escrita: " + e.getMessage());
		}
	}
	
	 public static void lerArquivo(ListaTrajetos trajetos, ListaTrechos trechos , ListaPontosDeParada pontos) throws PontoNaoEncontrado, TrechoJaCadastrado, TrajetoJaCadastrado, TrechoNaoEncontrado {
	        JSONArray json = new JSONArray();
	        try {
	            FileReader fr = new FileReader("./DadosJSON/LTrajetos.json");
	            BufferedReader br = new BufferedReader(fr);
	            if (!br.ready()) {
	                return;
	            }
	            json = new JSONArray(br.readLine());
	            br.close();
	        } catch (IOException e) {
	            System.out.println("Erro ao abrir arquivo de trajetos para leitura: " + e.getMessage());
	        }

	        for (int i = 0; i < json.length(); i++) {
	            int id = json.getJSONObject(i).getInt("id");
	            int idCheckpoint = json.getJSONObject(i).getInt("idCheckpoint");
	            PontoDeParada checkpointPonto = pontos.buscar(idCheckpoint);
	            int tempoChegada = json.getJSONObject(i).getInt("tempoChegada");
	            Checkpoint checkpoint = new Checkpoint(checkpointPonto,tempoChegada);

	            Trajeto trajeto = new Trajeto(id,checkpoint);

	            JSONArray trechosArray = json.getJSONObject(i).getJSONArray("trechos");
	            for (int j = 0; j < trechosArray.length(); j++) {
	                int idTrecho = trechosArray.getJSONObject(j).getInt("trecho");
	                Trecho  trecho = trechos.bucar(idTrecho);
	           
	                trajeto.adiciona(trecho,true);
	            }
				if(id>Trajeto.qntdIDsGerados) {
					Trajeto.qntdIDsGerados = id;
				}

	            trajetos.adiciona(trajeto,true);
	        }
	        
	 }
	 
	

}
