package br.com.cepedi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.cepedi.dao.FaturaDAO;
import br.com.cepedi.dao.RelogioDAO;
import br.com.cepedi.verificacoes.geral.VerificacoesGeral;

public class Imovel {
	
	//------ATRIBUTOS
	
	private Cliente proprietario = null;
	private int id;
	private String matricula;
	Relogio relogio;
	List<Fatura> faturas;
	
	
	//----CONSTRUTORES
	
	
	public Imovel(String matricula) {
		super();
		setMatricula(matricula);
		relogio = new Relogio();
		faturas = new ArrayList<Fatura>();
	}
	
	public Imovel(int id ,String matricula, Endereco endereco, Relogio relogio , Cliente cliente) {
		super();
		this.matricula = matricula;
		this.relogio = relogio;
		this.proprietario = cliente;
		this.id = id;
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
		return "Imovel [proprietario=" + proprietario + ", id=" + id + ", matricula=" + matricula ;
	}





	
	//-----EQUALS AND HASHCODE
	
	@Override
	public int hashCode() {
		return Objects.hash(faturas, id, matricula, proprietario, relogio);
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
		return Objects.equals(faturas, other.faturas) && id == other.id && Objects.equals(matricula, other.matricula)
				&& Objects.equals(proprietario, other.proprietario) && Objects.equals(relogio, other.relogio);
	}




	
	// IMOVEL REALIZANDO LEITURA
	
	public void realizaLeitura(LocalDate data , BigDecimal novoValor) {
		
		relogio.registraNovaLeitura(novoValor);
		RelogioDAO.atualizaRelogio(relogio);
		
		Fatura fatura = new Fatura(data,relogio);
		
		FaturaDAO.adicionarFatura(fatura,relogio.getId());
	
	}
	
	//------BUSCAS NA LISTA DE FATURAS
	




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
