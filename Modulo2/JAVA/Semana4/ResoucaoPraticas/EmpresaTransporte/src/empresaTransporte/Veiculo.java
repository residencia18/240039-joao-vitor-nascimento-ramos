package empresaTransporte;

public class Veiculo {
	
	private static int numeroDeVeiculos;
	
	private int id;
	private String renavan;
	private String marca;
	private String placa;
	private int ano;
	private Boolean isDanificado;
	private int quantidaadeDeAssentos; // Mesmo que nunca respeitada, pode ser utilizada para algum levantamento estatistico
	
	
	public Veiculo(String renavan, String marca, String placa, int ano, Boolean isDanificado,
			int quantidaadeDeAssentos) {
		numeroDeVeiculos++;
		id = numeroDeVeiculos;
		this.renavan = renavan;
		this.marca = marca;
		this.placa = placa;
		this.ano = ano;
		this.isDanificado = isDanificado;
		this.quantidaadeDeAssentos = quantidaadeDeAssentos;
	}




	public int getId() {
		return id;
	}



	public String getRenavan() {
		return renavan;
	}


	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public Boolean getIsDanificado() {
		return isDanificado;
	}


	public void setIsDanificado(Boolean isDanificado) {
		this.isDanificado = isDanificado;
	}


	public int getQuantidaadeDeAssentos() {
		return quantidaadeDeAssentos;
	}


	public void setQuantidaadeDeAssentos(int quantidaadeDeAssentos) {
		this.quantidaadeDeAssentos = quantidaadeDeAssentos;
	}
	
	
	
	

}
