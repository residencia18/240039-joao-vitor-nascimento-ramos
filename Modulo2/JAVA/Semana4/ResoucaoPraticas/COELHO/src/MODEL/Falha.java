package MODEL;

import java.util.Date;

public class Falha {
	private int id;
    private String descricao;
    private Date previsao;
    private Date dataInicio;
    private Date dataFim;
    private Imovel imovel;
    
    
	public Falha(int id, String descricao, Date previsao, Date dataInicio, Date dataFim, Imovel imovel) {
		this.id = id;
		this.descricao = descricao;
		this.previsao = previsao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.imovel = imovel;
	}
	
	public Falha() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
}
