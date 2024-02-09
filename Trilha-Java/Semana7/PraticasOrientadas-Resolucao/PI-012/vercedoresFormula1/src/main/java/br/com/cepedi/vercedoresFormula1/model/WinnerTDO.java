package br.com.cepedi.vercedoresFormula1.model;

public class WinnerTDO implements Comparable<WinnerTDO>{
	
	private String country;
	private String name;
	private Integer qtdVictory;
	
	public WinnerTDO(String country, String name, Integer qtdVictory) {
		super();
		this.country = country;
		this.name = name;
		this.qtdVictory = qtdVictory;
	}
	
	
	public String getCountry() {
		return country;
	}
	public String getName() {
		return name;
	}
	public Integer getQtdVictory() {
		return qtdVictory;
	}


	@Override
	public String toString() {
		return "Piloto [country=" + country + ", name=" + name + ", qtdVictory=" + qtdVictory + "]";
	}


	@Override
	public int compareTo(WinnerTDO o) {
		return Integer.compare(qtdVictory, o.getQtdVictory());
	}

	
	
	
}
