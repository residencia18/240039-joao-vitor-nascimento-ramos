package MODEL;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fatura {
    private int id;
    private LocalDate data;
    private int ultimaLeitura;
    private int penultimaLeitura;
    private double valor;
    private boolean quitado;
    private Imovel imovel;
    ArrayList<Pagamento> listaPagamentos;

    public Fatura(int ultimaLeitura, int penultimaLeitura, Imovel imovel) {
        
        this.data = LocalDate.now();
        this.ultimaLeitura = ultimaLeitura;
        this.penultimaLeitura = penultimaLeitura;
        CalcularValor();
        this.quitado = false;
        this.imovel = imovel;
        listaPagamentos = new ArrayList<>();
    }

    public Fatura() {
    	listaPagamentos = new ArrayList<>();
    	this.quitado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getUltimaLeitura() {
        return ultimaLeitura;
    }

    public void setUltimaLeitura(int ultimaLeitura) {
        this.ultimaLeitura = ultimaLeitura;
    }

    public int getPenultimaLeitura() {
        return penultimaLeitura;
    }

    public void setPenultimaLeitura(int penultimaLeitura) {
        this.penultimaLeitura = penultimaLeitura;
    }

    public double getValor() {
        return valor;
    }
    
	public void setValor(double valor) {
		this.valor = valor;
	}
    
    public void CalcularValor() {
		if (this.ultimaLeitura > this.penultimaLeitura) {
			this.valor = (this.ultimaLeitura - this.penultimaLeitura)*10;
		}else {
			this.valor = (this.penultimaLeitura - this.ultimaLeitura)*10;
		}
    }

    public boolean isQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }
}
