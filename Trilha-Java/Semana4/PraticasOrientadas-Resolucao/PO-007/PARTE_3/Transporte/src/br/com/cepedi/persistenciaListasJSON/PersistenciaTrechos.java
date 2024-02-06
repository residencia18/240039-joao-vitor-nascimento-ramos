package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.listaPontosDeParada.PontoNaoEncontrado;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trecho;

public abstract class PersistenciaTrechos {
	
	public static void salvarArquivo(ListaTrechos trechos) {
		
		JSONArray json = new JSONArray();

		for (Trecho trecho : trechos) {
			JSONObject o = new JSONObject();
			o.put("id", trecho.getId());
			o.put("minutos", trecho.getMinutos());
			o.put("origem", trecho.getOrigem().getId());
			o.put("destino", trecho.getDestino().getId());
			json.put(o);
		}

		try {
			FileWriter fw = new FileWriter("./DadosJSON/Ltrechos.json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de trechos para escrita: " + e.getMessage());
		}
	}
	
	public static void lerArquivo(ListaTrechos trechos , ListaPontosDeParada pontos) throws PontoNaoEncontrado  {
		JSONArray json = new JSONArray();
		try {
			FileReader fr = new FileReader("./DadosJSON/Ltrechos.json");
			BufferedReader br = new BufferedReader(fr);
			if(!br.ready()) {
				return;
			}
			json = new JSONArray(br.readLine());
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de trechos  para leitura: " + e.getMessage());
		}
		
		
		for (int i = 0; i < json.length(); i++) {
			int id = json.getJSONObject(i).getInt("id");
			if(id>Trecho.qtdTrechos) {
				Trecho.qtdTrechos = id;
			}
			int minutos = json.getJSONObject(i).getInt("minutos");
			int idOrigem = json.getJSONObject(i).getInt("origem");
			int idDestino = json.getJSONObject(i).getInt("destino");
			PontoDeParada origem = pontos.buscar(idOrigem);
			PontoDeParada destino = pontos.buscar(idDestino);
			Trecho trecho =  new Trecho(id,origem,destino,minutos);
			trechos.add(trecho);
		}
	}
	
	
	
	

}
