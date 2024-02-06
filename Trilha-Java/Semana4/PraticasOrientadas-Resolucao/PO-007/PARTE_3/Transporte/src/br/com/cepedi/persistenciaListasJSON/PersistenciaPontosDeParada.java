package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.veiculo.modeloVeiculoInvalidoException;
import br.com.cepedi.exceptions.veiculo.montadoraVeiculoInvalidaException;
import br.com.cepedi.exceptions.veiculo.placaVeiculoInvalidaException;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.veiculo.Veiculo;

public abstract class PersistenciaPontosDeParada {

	public static void salvarArquivo(ListaPontosDeParada pontos) {
		
		JSONArray json = new JSONArray();

		for (PontoDeParada ponto : pontos) {
			JSONObject o = new JSONObject();
			o.put("id", ponto.getId());
			o.put("nome", ponto.getNome());
			json.put(o);
		}

		try {
			FileWriter fw = new FileWriter("./DadosJSON/LpontosDeParada.json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de pontos de parada para escrita: " + e.getMessage());
		}
	}
	
	
	public static void lerArquivo(ListaPontosDeParada pontos)  {
		JSONArray json = new JSONArray();
		try {
			FileReader fr = new FileReader("./DadosJSON/LpontosDeParada.json");
			BufferedReader br = new BufferedReader(fr);
			if(!br.ready()) {
				return;
			}
			json = new JSONArray(br.readLine());
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de pontos de parada  para leitura: " + e.getMessage());
		}
		
		
		for (int i = 0; i < json.length(); i++) {
			int id = json.getJSONObject(i).getInt("id");
			if(id>PontoDeParada.qtdPontosParada) {
				PontoDeParada.qtdPontosParada = id;
			}
			String nome = json.getJSONObject(i).getString("nome");
			PontoDeParada ponto = new PontoDeParada(id,nome);
			pontos.add(ponto);
		}
	}
	
	
	
}
