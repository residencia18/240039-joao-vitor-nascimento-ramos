package br.com.cepedi.quadradoMagico;

public class Quadrado {
	
	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Quadrado(String estado) {
		this.estado = estado;
	}

	public void clique(int index) {
		switch(index) {
			case 0:
				inverte(1);
		}
	}
	
	
	protected void inverte(int index) {
		StringBuilder str = new StringBuilder(estado);
		char c = estado.charAt(index);
		c = (c == 'X') ? 'O' : 'X';
		str.setCharAt(index, c);
		this.estado = str.toString();
	}
	
	

}
