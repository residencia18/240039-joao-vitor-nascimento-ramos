package br.com.alura.tabelaFipe.principal;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.alura.tabelaFipe.model.Dados;
import br.com.alura.tabelaFipe.model.Modelos;
import br.com.alura.tabelaFipe.model.Veiculo;
import br.com.alura.tabelaFipe.service.ConsumoApi;
import br.com.alura.tabelaFipe.service.ConverteDados;

public class Principal {

    private static final String MENU_PRINCIPAL = """
            *** OPÇÕES ***
            Carro
            Moto
            Caminhão
            Digite uma das opções para consulta :
            """;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public static void main(String[] args) {
        exibeMenu();
    }

    public static void exibeMenu() {
        System.out.println(MENU_PRINCIPAL);
        String opcao = obterOpcao();
        String endereco = obterEndereco(opcao);
        List<Dados> marcasVeiculo = obterMarcasVeiculo(endereco);
        exibirMarcasVeiculo(marcasVeiculo);
        String codigoMarca = obterCodigoMarca();
        endereco = atualizaEnderecoPraObterModelos(codigoMarca,endereco);
        Modelos listaModelos = obterModelos(endereco);
        exibirModelosVeiculo(listaModelos);
        String trecho = obterTrechoConsulta();
        List<Dados> modelosFiltrados = filtrarModelos(listaModelos, trecho);
        exibirModelosFiltrados(modelosFiltrados);
        String codigoModelo = obterCodigoModelo();
        endereco = atualizarEnderecoComCodigoModelo(endereco, codigoModelo);
        List<Dados> anos = ObterModeloVeiculoPorCodigoEAno(endereco);
        List<Veiculo> veiculos = obterVeiculosPorAno(anos,endereco);
        veiculos.stream().forEach(System.out::println);
        
    }
    
    private static List<Veiculo> obterVeiculosPorAno(List<Dados> anos, String endereco) {
        return anos.parallelStream()
                .map(ano -> {
                    String enderecoAno = endereco + "/" + ano.codigo();
                    String json = ConsumoApi.obterDados(enderecoAno);
                    try {
                        return ConverteDados.obterDados(json, Veiculo.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull) 
                .collect(Collectors.toList());
    }
    
    private static List<Dados> ObterModeloVeiculoPorCodigoEAno(String endereco) {
        String json = ConsumoApi.obterDados(endereco);
        try {
            return ConverteDados.obterLista(json, Dados.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static void exibirModelosVeiculo(Modelos listaModelos) {
    	listaModelos.modelos().forEach(System.out::println);
    }

    private static String obterOpcao() {
        return SCANNER.nextLine().toLowerCase();
    }

    private static String obterEndereco(String opcao) {
        if (opcao.contains("carr")) {
            return URL_BASE + "carros/marcas";
        } else if (opcao.contains("mot")) {
            return URL_BASE + "motos/marcas";
        } else {
            return URL_BASE + "caminhoes/marcas";
        }
    }

    private static List<Dados> obterMarcasVeiculo(String endereco) {
        String json = ConsumoApi.obterDados(endereco);
        try {
            return ConverteDados.obterLista(json, Dados.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void exibirMarcasVeiculo(List<Dados> marcasVeiculo) {
        if (marcasVeiculo != null) marcasVeiculo.forEach(System.out::println);
    }
    
    private static String atualizaEnderecoPraObterModelos(String codigoMarca, String endereco) {
    	return endereco += "/" + codigoMarca + "/modelos";
    }

    private static Modelos obterModelos(String endereco) {
        String json = ConsumoApi.obterDados(endereco);
        try {
            return ConverteDados.obterDados(json, Modelos.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String obterCodigoMarca() {
        System.out.println("Informe o codigo da marca para consulta : ");
        return SCANNER.nextLine();
    }

    private static String obterTrechoConsulta() {
        System.out.println("Digite um trecho do nome do carro a ser mostrado : ");
        return SCANNER.nextLine();
    }

    private static List<Dados> filtrarModelos(Modelos listaModelos, String trecho) {
        if (listaModelos != null) {
            return listaModelos.modelos().parallelStream()
                    .filter(d -> d.nome().toLowerCase().contains(trecho.toLowerCase()))
                    .toList();
        }
        return null;
    }

    private static void exibirModelosFiltrados(List<Dados> modelosFiltrados) {
        if (modelosFiltrados != null) {
            modelosFiltrados.forEach(System.out::println);
        }
    }

    private static String obterCodigoModelo() {
        System.out.println("Digite o codigo do modelo : ");
        return SCANNER.nextLine();
    }

    private static String atualizarEnderecoComCodigoModelo(String endereco, String codigoModelo) {
        return endereco + "/" + codigoModelo + "/anos";
    }
}
