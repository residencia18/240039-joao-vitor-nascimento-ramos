package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.listaFuncionarios.FuncionarioNaoEncontrado;
import br.com.cepedi.exceptions.listaJornada.JornadaJaCadastrada;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoJaCadastrado;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoNaoEncontrado;
import br.com.cepedi.exceptions.listaVeiculos.VeiculoNaoEncontrado;
import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.listas.ListaJornadaTrajetoHorario;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.veiculo.Veiculo;

public abstract class PersistenciaJornadas {

	public static void salvarArquivo(ListaJornadas jornadas , ListaJornadaTrajetoHorario listaJornadaTrajetoHorario) {
	    JSONArray json = new JSONArray();

	    for (Jornada jornada : jornadas) {
	        JSONObject o = new JSONObject();
	        o.put("id", jornada.getId());
	        o.put("veiculo", jornada.getVeiculo().getId());
	        if(jornada.getCobrador()!=null) {
		        o.put("cobrador", jornada.getCobrador().getId());
	        }else {
	        	o.put("cobrador", 0);
	        }
	        o.put("motorista", jornada.getMotorista().getId());
	        o.put("nome", jornada.getNome());
	        o.put("dataInicio", jornada.getDataInicio().toString());

	        JSONArray trajetosArray = new JSONArray();
	        for (Trajeto trajeto : jornada.getTrajetos()) {
	            JSONObject trajetoJSON = new JSONObject();
	            trajetoJSON.put("trajeto", trajeto.getId());
	            trajetosArray.put(trajetoJSON);
	        }
	        o.put("trajetos", trajetosArray);

	        json.put(o);
	    }

	    try {
	        FileWriter fw = new FileWriter("./DadosJSON/LJornadas.json");
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(json.toString());
	        bw.close();
	        PersistenciaJornadaTrajetoHorario.salvarArquivo(listaJornadaTrajetoHorario);
	    } catch (IOException e) {
	        System.out.println("Erro ao abrir arquivo de jornadas para escrita: " + e.getMessage());
	    }
	    
	    
	}
	
	public static void salvarArquivo(ListaJornadas jornadas ) {
	    JSONArray json = new JSONArray();

	    for (Jornada jornada : jornadas) {
	        JSONObject o = new JSONObject();
	        o.put("id", jornada.getId());
	        o.put("veiculo", jornada.getVeiculo().getId());
	        if(jornada.getCobrador()!=null) {
		        o.put("cobrador", jornada.getCobrador().getId());
	        }else {
	        	o.put("cobrador", 0);
	        }
	        o.put("motorista", jornada.getMotorista().getId());
	        o.put("nome", jornada.getNome());
	        o.put("dataInicio", jornada.getDataInicio().toString());

	        JSONArray trajetosArray = new JSONArray();
	        for (Trajeto trajeto : jornada.getTrajetos()) {
	            JSONObject trajetoJSON = new JSONObject();
	            trajetoJSON.put("trajeto", trajeto.getId());
	            trajetosArray.put(trajetoJSON);
	        }
	        o.put("trajetos", trajetosArray);

	        json.put(o);
	    }

	    try {
	        FileWriter fw = new FileWriter("./DadosJSON/LJornadas.json");
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(json.toString());
	        bw.close();
	    } catch (IOException e) {
	        System.out.println("Erro ao abrir arquivo de jornadas para escrita: " + e.getMessage());
	    }
	    
	    
	}


	public static void lerArquivo(ListaJornadas jornadas, ListaTrechos trechos, ListaPontosDeParada pontos, ListaVeiculos veiculos, ListaFuncionarios funcionarios, ListaTrajetos trajetos) throws VeiculoNaoEncontrado, FuncionarioNaoEncontrado, JornadaJaCadastrada, TrajetoJaCadastrado, TrajetoNaoEncontrado {
	    JSONArray json = new JSONArray();
	    try {
	        FileReader fr = new FileReader("./DadosJSON/LJornadas.json");
	        BufferedReader br = new BufferedReader(fr);
	        if (!br.ready()) {
	            return;
	        }
	        json = new JSONArray(br.readLine());
	        br.close();
	    } catch (IOException e) {
	        System.out.println("Erro ao abrir arquivo de jornadas para leitura: " + e.getMessage());
	    }

	    for (int i = 0; i < json.length(); i++) {
	        int id = json.getJSONObject(i).getInt("id");
	        int idVeiculo = json.getJSONObject(i).getInt("veiculo");
	        Veiculo veiculo = veiculos.buscar(idVeiculo);
	        int idCobrador = json.getJSONObject(i).getInt("cobrador");
	        int idMotorista = json.getJSONObject(i).getInt("motorista");

	        Motorista motorista = (Motorista) funcionarios.buscar(idMotorista);
	        String nome = json.getJSONObject(i).getString("nome");
	        LocalDateTime dataInicio = LocalDateTime.parse(json.getJSONObject(i).getString("dataInicio"));
	        Cobrador cobrador = null; // Inicializa como nulo
	        Jornada jornada;

	        if (idCobrador != 0) {
	            cobrador = (Cobrador) funcionarios.buscar(idCobrador);
	        }

	        jornada = (cobrador != null) ? new Jornada(veiculo, cobrador, motorista, nome, dataInicio) :
	                new Jornada(veiculo, motorista, nome, dataInicio);

	        // Adiciona trajetos à jornada
	        JSONArray trajetosArray = json.getJSONObject(i).getJSONArray("trajetos");
	        for (int j = 0; j < trajetosArray.length(); j++) {
	            int idTrajeto = trajetosArray.getJSONObject(j).getInt("trajeto");
                Trajeto trajeto = trajetos.buscar(idTrajeto);
                jornada.getTrajetos().adiciona(trajeto,true);
	            
	        }
			if(id>Jornada.qtdIDsGerados) {
				Jornada.qtdIDsGerados = id;
			}

	        jornadas.adiciona(jornada);
	    }
	}

}
