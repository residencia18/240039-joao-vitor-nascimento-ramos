package br.com.cepedi;

public class Smartphone implements Comparable<Smartphone>{
	private String serialNumber;
	private String marca;
	public Smartphone(String serialNumber, String marca) {
		super();
		this.serialNumber = serialNumber;
		this.marca = marca;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(this==obj) return true;
		if(this.getClass() != obj.getClass()) return false;
		Smartphone smart = (Smartphone) obj; 
		return serialNumber!=null && serialNumber.equals(smart.getSerialNumber());
	} 
	
	@Override
	public int hashCode() {
		return serialNumber==null ? 0 :  this.serialNumber.hashCode();
	}
	@Override
	public int compareTo(Smartphone o) {
		return this.serialNumber.compareTo(o.getSerialNumber());
	}
	@Override
	public String toString() {
		return "Smartphone [serialNumber=" + serialNumber + ", marca=" + marca + "]";
	}


	
	
}
