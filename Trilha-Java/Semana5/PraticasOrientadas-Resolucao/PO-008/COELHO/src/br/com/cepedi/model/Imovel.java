package br.com.cepedi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.cepedi.verificacoes.geral.VerificacoesGeral;

public class Imovel {
	
	private Cliente proprietario = null;
	public static int qntIdsGerados=0;
	private int id;
	private String matricula;
	Endereco endereco;
	Relogio relogio;
	List<Fatura> faturas;
	
	
	//----CONSTRUTORES
	
	public Imovel(String matricula, Endereco endereco) {
		super();
		setMatricula(matricula);
		setEndereco(endereco);
		relogio = new Relogio();
		faturas = new ArrayList<Fatura>();
		qntIdsGerados++;
		this.id = qntIdsGerados;
	}
	
	public Imovel(int id ,String matricula, Endereco endereco, Relogio relogio , Cliente cliente) {
		super();
		this.matricula = matricula;
		this.endereco = endereco;
		this.relogio = relogio;
		this.proprietario = cliente;
		this.id = id;
		corrigeGeradorID(id);
	}

	private void corrigeGeradorID(int id) {
		if(id > qntIdsGerados) {
			qntIdsGerados =id;
		}
	}
	
	//---------GETTERS AND SETTERS

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		VerificacoesGeral.verificaID(id);	
		this.id = id;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		VerificacoesGeral.verificaStringVaziaOuNula(matricula);
		this.matricula = matricula;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		if(endereco==null) throw new IllegalArgumentException("Endereço nulo não é permitido");
		this.endereco = endereco;
	}

	public Relogio getRelogio() {
		return relogio;
	}

	public void setRelogio(Relogio relogio) {
		if(relogio==null) throw new IllegalArgumentException("Relogio nulo não é permitido");

		this.relogio = relogio;
	}
	
	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Cliente proprietario) {
		if(proprietario==null) throw new IllegalArgumentException("Cliente nulo não é permitido");

		this.proprietario = proprietario;
	}
	
	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}
	
	
	//-----TO STRING





	@Override
	public String toString() {
		return "Imovel [id=" + id + ", matricula=" + matricula + ", endereco=" + endereco + ", relogio=" + relogio
				+ "]";
	}


	
	//-----EQUALS AND HASHCODE
	


	@Override
	public int hashCode() {
		return Objects.hash(endereco);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imovel other = (Imovel) obj;
		return Objects.equals(endereco, other.endereco);
	}
	
	public void realizaLeitura(LocalDate data , BigDecimal novoValor) {
		
		relogio.registraNovaLeitura(novoValor);
		
		Fatura fatura = new Fatura(data,relogio);
		
		this.faturas.add(fatura);
		
	}
	
	public Fatura buscarFaturaEmAberto(int id) {
	    return faturas.stream().filter(fatura -> !fatura.isQuitado())
	                  .filter(fatura -> fatura.getId() == id)
	                  .findFirst()
	                  .orElseThrow(() -> new IllegalArgumentException("Fatura não encontrada com o ID: " + id));
	}

	public Fatura buscarFatura(int id) {
	    return faturas.stream().filter(fatura -> fatura.getId() == id)
	                  .findFirst()
	                  .orElseThrow(() -> new IllegalArgumentException("Fatura não encontrada com o ID: " + id));
	}
	
	

}
