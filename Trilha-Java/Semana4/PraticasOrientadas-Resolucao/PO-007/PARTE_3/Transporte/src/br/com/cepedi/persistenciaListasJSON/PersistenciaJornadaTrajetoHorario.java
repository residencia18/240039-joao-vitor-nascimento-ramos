package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.listaJornada.JornadaNaoEncontrada;
import br.com.cepedi.exceptions.listaJornadaTrajetoHorario.JornadaTrajetoHorarioException;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoNaoEncontrado;
import br.com.cepedi.model.listas.ListaJornadaTrajetoHorario;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.transporte.JornadaTrajetoHorario;
import br.com.cepedi.model.transporte.Trajeto;

public abstract class PersistenciaJornadaTrajetoHorario {

	public static void salvarArquivo(ListaJornadaTrajetoHorario listaJornadaTrajetoHorario) {
		JSONArray json = new JSONArray();

		for (JornadaTrajetoHorario jornadaTrajetoHorario : listaJornadaTrajetoHorario) {
			JSONObject o = new JSONObject();
			o.put("idJornada", jornadaTrajetoHorario.getJornada().getId());
			o.put("idTrajeto",  jornadaTrajetoHorario.getTrajeto().getId());
			o.put("horarioInicioTrajeto", jornadaTrajetoHorario.getHorarioInicioTrajeto());
			json.put(o);
		}

		try {
			FileWriter fw = new FileWriter("./DadosJSON/LJornadaTrajetoHorario.json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de jornadaTrajetoHorario para escrita: " + e.getMessage());
		}
	}	
	
	public static void lerArquivo(ListaJornadaTrajetoHorario listaJornadaTrajetoHorario, ListaJornadas jornadas ,ListaTrajetos trajetos) throws TrajetoNaoEncontrado, JornadaNaoEncontrada, JornadaTrajetoHorarioException  {
	    JSONArray json = new JSONArray();
	    try {
	        FileReader fr = new FileReader("./DadosJSON/LJornadaTrajetoHorario.json");
	        BufferedReader br = new BufferedReader(fr);
	        if (!br.ready()) {
	            return;
	        }
	        json = new JSONArray(br.readLine());
	        br.close();
	    } catch (IOException e) {
	        System.out.println("Erro ao abrir arquivo de jornadaTrajetoHorario para leitura: " + e.getMessage());
	    }

	    for (int i = 0; i < json.length(); i++) {
	        int idJornada = json.getJSONObject(i).getInt("idJornada");
	        int idTrajeto = json.getJSONObject(i).getInt("idTrajeto");
	        LocalDateTime horarioInicioTrajeto = LocalDateTime.parse(json.getJSONObject(i).getString("horarioInicioTrajeto"));


	        Jornada jornada = jornadas.buscar(idJornada);
	        Trajeto trajeto = trajetos.buscar(idTrajeto);
	        
	        JornadaTrajetoHorario jornadaTrajetoHorario = new JornadaTrajetoHorario(jornada,trajeto,horarioInicioTrajeto);
	        
	        listaJornadaTrajetoHorario.adiciona(jornadaTrajetoHorario);
	        
	    }
	}

	
}
