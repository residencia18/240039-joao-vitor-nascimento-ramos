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
import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.model.veiculo.Veiculo;

public abstract class PersistenciaVeiculos {

	public static void salvarArquivo(ListaVeiculos veiculos) {
			JSONArray json = new JSONArray();

			for (Veiculo veiculo : veiculos) {
				JSONObject o = new JSONObject();
				o.put("id", veiculo.getId());
				o.put("placa", veiculo.getPlaca());
				o.put("modelo", veiculo.getModelo());
				o.put("montadora", veiculo.getMontadora());
				json.put(o);
			}

			try {
				FileWriter fw = new FileWriter("./DadosJSON/LVeiculos.json");
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(json.toString());
				bw.close();
			} catch (IOException e) {
				System.out.println("Erro ao abrir arquivo de veiculos para escrita: " + e.getMessage());
			}
	}
	
	public static void lerArquivo(ListaVeiculos lista) throws modeloVeiculoInvalidoException, montadoraVeiculoInvalidaException, placaVeiculoInvalidaException {
		JSONArray json = new JSONArray();
		try {
			FileReader fr = new FileReader("./DadosJSON/LVeiculos.json");
			BufferedReader br = new BufferedReader(fr);
			if(!br.ready()) {
				return;
			}
			json = new JSONArray(br.readLine());
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de veiculos para leitura: " + e.getMessage());
		}
		for (int i = 0; i < json.length(); i++) {
			int id = json.getJSONObject(i).getInt("id");
			if(id>Veiculo.numeroDeVeiculos) {
				Veiculo.numeroDeVeiculos = id;
			}
			String placa = json.getJSONObject(i).getString("placa");
			String modelo = json.getJSONObject(i).getString("modelo");
			String montadora = json.getJSONObject(i).getString("montadora");
			Veiculo novo = new Veiculo(id,modelo,placa,montadora);
			lista.add(novo);
		}
	}
	
}
