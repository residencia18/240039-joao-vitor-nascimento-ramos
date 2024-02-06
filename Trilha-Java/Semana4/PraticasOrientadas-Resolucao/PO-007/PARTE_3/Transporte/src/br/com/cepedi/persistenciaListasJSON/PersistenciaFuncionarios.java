package br.com.cepedi.persistenciaListasJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.exceptions.funcionario.SalarioFuncionarioInvalidoException;
import br.com.cepedi.exceptions.motorista.CNHMotoristaInvalidaException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;
import br.com.cepedi.exceptions.veiculo.modeloVeiculoInvalidoException;
import br.com.cepedi.exceptions.veiculo.montadoraVeiculoInvalidaException;
import br.com.cepedi.exceptions.veiculo.placaVeiculoInvalidaException;
import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Funcionario;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.pessoa.Pessoa;

public abstract class PersistenciaFuncionarios {

    public static void salvarArquivo(ListaFuncionarios funcionarios) {
        JSONArray jsonMotorista = new JSONArray();
        JSONArray jsonCobrador = new JSONArray();

        for (Funcionario funcionario : funcionarios) {
            JSONObject o = new JSONObject();
            o.put("id", funcionario.getId());
            o.put("nome", funcionario.getNome());
            o.put("cpf", funcionario.getCPF());

            if (funcionario instanceof Motorista) {
                Motorista motor = (Motorista) funcionario;
                o.put("cnh", motor.getCNH());
                o.put("salario", motor.getSalario());
                jsonMotorista.put(o);
            } else if (funcionario instanceof Cobrador) {
                Cobrador cobrador = (Cobrador) funcionario;
                o.put("salario", cobrador.getSalario());
                jsonCobrador.put(o);
            }
        }

        salvarJSON(jsonMotorista, "./DadosJSON/LMotoristas.json");
        salvarJSON(jsonCobrador, "./DadosJSON/LCobradores.json");
    }
    
	public static void lerArquivo(ListaFuncionarios funcionarios) throws modeloVeiculoInvalidoException, montadoraVeiculoInvalidaException, placaVeiculoInvalidaException, NomePessoaInvalidoException, CPFPessoaInvalidoException, SalarioFuncionarioInvalidoException, CNHMotoristaInvalidaException {
        JSONArray jsonMotorista = new JSONArray();
        JSONArray jsonCobrador = new JSONArray();
        
        try {
			FileReader fr = new FileReader("./DadosJSON/LMotoristas.json");
			BufferedReader br = new BufferedReader(fr);
			if(!br.ready()) {
				return;
			}
			jsonMotorista = new JSONArray(br.readLine());
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de motoristas para leitura: " + e.getMessage());
		}
        
        try {
			FileReader fr = new FileReader("./DadosJSON/LCobradores.json");
			BufferedReader br = new BufferedReader(fr);
			if(!br.ready()) {
				return;
			}
			jsonCobrador = new JSONArray(br.readLine());
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de cobradores para leitura: " + e.getMessage());
		}
        
        
		for (int i = 0; i < jsonMotorista.length(); i++) {
			int id = jsonMotorista.getJSONObject(i).getInt("id");
			if(id>Pessoa.numeroDePessoas) {
				Pessoa.numeroDePessoas = id;
			}
			String nome = jsonMotorista.getJSONObject(i).getString("nome");
			String cpf = jsonMotorista.getJSONObject(i).getString("cpf");
			BigDecimal salario = jsonMotorista.getJSONObject(i).getBigDecimal("salario");
			String cnh = jsonMotorista.getJSONObject(i).getString("cnh");
			Motorista novo = new Motorista(id,nome,cpf,salario,cnh);
			funcionarios.add(novo);
		}
		
		for (int i = 0; i < jsonCobrador.length(); i++) {
			int id = jsonCobrador.getJSONObject(i).getInt("id");
			if(id>Pessoa.numeroDePessoas) {
				Pessoa.numeroDePessoas = id;
			}
			String nome = jsonCobrador.getJSONObject(i).getString("nome");
			String cpf = jsonCobrador.getJSONObject(i).getString("cpf");
			BigDecimal salario = jsonCobrador.getJSONObject(i).getBigDecimal("salario");
			Cobrador novo = new Cobrador(id,nome,cpf,salario);
			funcionarios.add(novo);
		}
		
	}

    private static void salvarJSON(JSONArray jsonArray, String filePath) {
        try (FileWriter fw = new FileWriter(filePath);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(jsonArray.toString());
        } catch (IOException e) {
            System.out.println("Erro ao abrir arquivo para escrita: " + e.getMessage());
        }
    }
}
