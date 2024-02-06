package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.passageiro.CartaoInvalidoException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;
import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.passagem.CartaoPassagem;
import br.com.cepedi.model.passagem.TipoCartao;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.model.pessoa.Pessoa;

public abstract class PersistenciaPassageiros {



	public static void salvarArquivo(ListaPassageiros passageiros) {
		
		JSONArray json = new JSONArray();

		for (Passageiro passageiro : passageiros) {
			JSONObject o = new JSONObject();
			o.put("id", passageiro.getId());
			o.put("nome", passageiro.getNome());
			o.put("cpf", passageiro.getCPF());
			o.put("tipoCartao", passageiro.getCartao().getTipo());
			o.put("saldoCartao", passageiro.getCartao().getSaldo());
			json.put(o);
		}

		try {
			FileWriter fw = new FileWriter("./DadosJSON/LPassageiros.json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de veiculos para escrita: " + e.getMessage());
		}
	}
	
	
	public static void lerArquivo(ListaPassageiros passageiros) throws NomePessoaInvalidoException, CPFPessoaInvalidoException, CartaoInvalidoException {
		JSONArray json = new JSONArray();
		try {
			FileReader fr = new FileReader("./DadosJSON/LPassageiros.json");
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
			if(id>Pessoa.numeroDePessoas) {
				Pessoa.numeroDePessoas = id;
			}
			String nome = json.getJSONObject(i).getString("nome");
			String cpf = json.getJSONObject(i).getString("cpf");
			BigDecimal saldo = json.getJSONObject(i).getBigDecimal("saldoCartao");
			String tipoString = json.getJSONObject(i).getString("tipoCartao");

	        TipoCartao tipoCartao = null;
	        switch (tipoString.toUpperCase()) {
	            case "TRANSPORTE":
	                tipoCartao = TipoCartao.TRANSPORTE;
	                break;
	            case "ESTUDANTIL":
	                tipoCartao = TipoCartao.ESTUDANTIL;
	                break;
	            case "IDOSO":
	                tipoCartao = TipoCartao.IDOSO;
	                break;
	            default:
	                throw new IllegalArgumentException("Valor de tipo de cartão inválido");
	        }

	        CartaoPassagem cartao = new CartaoPassagem(tipoCartao);
	        cartao.setSaldo(saldo);
			
			Passageiro novo = new Passageiro(id,nome,cpf,cartao);
			passageiros.add(novo);
		}
	}
}
