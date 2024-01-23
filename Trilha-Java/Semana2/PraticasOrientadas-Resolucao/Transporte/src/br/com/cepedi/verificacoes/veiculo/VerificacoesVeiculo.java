package br.com.cepedi.verificacoes.veiculo;

public class VerificacoesVeiculo {
	
	// Verifica se a placa possui formato adequado
	public static boolean verificaPlaca(String placa) {
		return placa.matches("[A-Z]{3}-\\d{4}");
	}
	
    // Verifica se existem apenas caracteres alfanumericos e espaços no nome do modelo do veículo
    public static boolean verificaModelo(String modelo) {
        return modelo.matches("[A-Za-z0-9\\s]+");
    }
	
	// Verifica se existem apenas caracteres alfabéticos e espaços no nome da montadora
	public static boolean verificaMontadora(String montadora) {
		return montadora.matches("[A-Za-z\\s]+");
	}
}
